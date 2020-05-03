package org.bookReminder.a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//@EnableMongoRepositories(basePackages = { "org.bookReminder.dao.mongo.repository" })
//@EnableJpaRepositories(basePackages = { "org.bookReminder.dao.jpa.repository" })
//@EntityScan(basePackages = { "org.bookReminder.dao.entity" })
//@ComponentScan(basePackages = { "org.bookReminder.dao.jpa.repository","org.bookReminder.dao.mongo.repository" })

@ComponentScan(basePackages = {"org.bookReminder.dao.jpa.repository"})



@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
