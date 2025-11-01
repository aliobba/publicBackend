package com.ooredoo.report_builder.services.services.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEnngine;

    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEnngine) {
        this.mailSender = mailSender;
        this.templateEnngine = templateEnngine;
    }

    @Async
    public void sendEmail(
            String to,
            String username,
            EmailTemplateName emailTemplate,
            String confirmationUrl,
            String activationCode,
            String subject) throws MessagingException {
        String templateName;
        if (emailTemplate != null) {
            templateName = emailTemplate.getName();
        } else {
            templateName = "activate_account"; // fallback
        }
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                MULTIPART_MODE_MIXED,
                UTF_8.name());
        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("confirmationUrl", confirmationUrl);
        properties.put("activationCode", activationCode);

        Context context = new Context();
        context.setVariables(properties);
        helper.setFrom("iheb.ayari@esprit.tn");
        helper.setTo(to);
        helper.setSubject(subject);

        String template = templateEnngine.process(templateName, context);

        helper.setText(template, true);

        mailSender.send(message);

    }

    public void sendWithSendGrid(
            String to,
            String username,
            EmailTemplateName emailTemplate,
            String confirmationUrl,
            String activationCode,
            String subject) throws IOException {
        try {
            log.info("Preparing email to send");

            String templateName = (emailTemplate != null) ? "activate_account" : emailTemplate.getName();

            // --- Clé & expéditeur depuis env (trim pour éviter espaces/copier-coller) ---
            String rawKey = System.getenv("SENDGRID_API_KEY");
            String apiKey = (rawKey == null) ? "" : rawKey.trim();
            String fromAddr = (System.getenv("MAIL_FROM") == null) ? "" : System.getenv("MAIL_FROM").trim();
            String region = (System.getenv("SENDGRID_REGION") == null) ? "us"
                    : System.getenv("SENDGRID_REGION").trim().toLowerCase();

            if (apiKey.isEmpty() || !apiKey.startsWith("SG.")) {
                log.error("SENDGRID_API_KEY missing or bad format");
                return;
            }
            if (fromAddr.isEmpty()) {
                log.error("MAIL_FROM missing");
                return;
            }
            log.info("sendgrid.init keyLen={}, region={}, fromDomain={}",
                    apiKey.length(), region,
                    fromAddr.contains("@") ? fromAddr.substring(fromAddr.indexOf('@') + 1) : "n/a");

            // --- Construire le HTML ---
            Map<String, Object> properties = new HashMap<>();
            properties.put("username", username);
            properties.put("confirmationUrl", confirmationUrl);
            properties.put("activationCode", activationCode);

            Context ctx = new Context();
            ctx.setVariables(properties);
            String html = templateEnngine.process(templateName, ctx);

            // --- Client SendGrid + région correcte ---
            SendGrid sg = new SendGrid(apiKey);
            // N'utilise PAS setDataResidency si tu n'es pas certain d'être en EU.
            // Préfère setHost selon l'env :
            if ("eu".equals(region)) {
                sg.setHost("api.eu.sendgrid.com");
            } else {
                sg.setHost("api.sendgrid.com");
            }

            // --- Requête ---
            Mail mail = new Mail(new Email(fromAddr), subject, new Email(to), new Content("text/html", html));
            Request req = new Request();
            req.setMethod(Method.POST);
            req.setEndpoint("mail/send");
            req.setBody(mail.build());

            Response resp = sg.api(req);
            log.info("sendgrid.response status={}, body={}", resp.getStatusCode(), resp.getBody());

        } catch (Exception e) {
            log.error("Email send failed", e);
            throw e;
        }
    }

}
