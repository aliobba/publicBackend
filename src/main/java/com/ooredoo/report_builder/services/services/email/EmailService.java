package com.ooredoo.report_builder.services.services.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

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
import org.slf4j.MDC;

import com.sendgrid.*;

@Service
public class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEnngine;

    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEnngine) {
        this.mailSender = mailSender;
        this.templateEnngine = templateEnngine;
    }

    public void sendWithSendGrid(String to,
            String username,
            EmailTemplateName emailTemplate,
            String confirmationUrl,
            String activationCode,
            String subject) throws IOException {
        try {
            String templateName;
            log.info("Preparing email to send");

            if (emailTemplate != null)
                templateName = "activate_account";
            else {
                templateName = emailTemplate.getName();
            }

            String apiKey = System.getenv("SENDGRID_API_KEY");

            if (apiKey == null || apiKey.isBlank()) {
                log.error("SENDGRID_API_KEY is missing");
                return;
            }
            if (!apiKey.startsWith("SG.")) {
                log.warn("SENDGRID_API_KEY format looks unusual");
            }

            Email from = new Email(System.getenv("MAIL_FROM"));
            Email toEmail = new Email(to);

            Map<String, Object> properties = new HashMap<>();
            properties.put("username", username);
            properties.put("confirmationUrl", confirmationUrl);
            properties.put("activationCode", activationCode);

            Context context = new Context();
            context.setVariables(properties);

            String template = templateEnngine.process(templateName, context);

            Mail mail = new Mail(from, subject, toEmail, new Content("text/html", template));

            SendGrid sg = new SendGrid(apiKey);
            sg.setDataResidency("eu");

            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response resp = sg.api(request);
            log.info("sendgrid.response status={}, bodySize={}", resp.getStatusCode(), resp.getBody().length());
        } catch (Exception e) {
            log.error("Email send failed", e);
            throw e;
        }

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

        try {

            log.info("Preparing email to send");

            if (emailTemplate != null)
                templateName = "activate_account";
            else {
                templateName = emailTemplate.getName();
            }

            log.debug("Email details: to={}, subject={}, template={}, confirmationUrl={}",
                    to, subject, templateName, confirmationUrl);

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
            log.info("Email sent");

        } catch (MessagingException ex) {
            log.error("Email send failed", ex);
            throw ex;
        }

    }

}
