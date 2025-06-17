package com.ooredoo.report_builder;

import com.ooredoo.report_builder.repo.RoleRepository;
import com.ooredoo.report_builder.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication(scanBasePackages = "com.ooredoo.report_builder")
@EnableJpaAuditing
@EnableAsync
public class BuliderReportApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuliderReportApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findByName("USER").isEmpty()) {
				roleRepository.save(
						Role.builder().name("USER").build()
				);
			}

			if (roleRepository.findByName("MAIN_ADMIN").isEmpty()) {
				roleRepository.save(
						Role.builder().name("MAIN_ADMIN").build()
				);
			}


			if (roleRepository.findByName("DEPARTMENT_ADMIN").isEmpty()) {
				roleRepository.save(
						Role.builder().name("DEPARTMENT_ADMIN").build()
				);
			}
		};
	}
}
