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
			if (roleRepository.findByName("SIMPLE_USER").isEmpty()) {
				roleRepository.save(
						Role.builder().name("SIMPLE_USER").build()
				);
			}

			if (roleRepository.findByName("MAIN_ADMIN").isEmpty()) {
				roleRepository.save(
						Role.builder().name("MAIN_ADMIN").build()
				);
			}
			if (roleRepository.findByName("ENTERPRISE_ADMIN").isEmpty()) {
				roleRepository.save(
						Role.builder().name("ENTERPRISE_ADMIN").build()
				);
			}
			if (roleRepository.findByName("HEAD_OF_SECTOR").isEmpty()) {
				roleRepository.save(
						Role.builder().name("HEAD_OF_SECTOR").build()
				);
			}
			if (roleRepository.findByName("HEAD_OF_ZONE").isEmpty()) {
				roleRepository.save(
						Role.builder().name("HEAD_OF_ZONE").build()
				);
			}
			if (roleRepository.findByName("HEAD_OF_REGION").isEmpty()) {
				roleRepository.save(
						Role.builder().name("HEAD_OF_REGION").build()
				);
			}
			if (roleRepository.findByName("HEAD_OF_POS").isEmpty()) {
				roleRepository.save(
						Role.builder().name("HEAD_OF_POS").build()
				);
			}
			if (roleRepository.findByName("COMMERCIAL_POS").isEmpty()) {
				roleRepository.save(
						Role.builder().name("COMMERCIAL_POS").build()
				);
			}

		};
	}
}
