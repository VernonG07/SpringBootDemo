package com.griffin.spring.oracle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		log.info("checking db connection...");
		
		jdbcTemplate.update("create table if not exists person(id INT NOT NULL AUTO_INCREMENT,firstname VARCHAR(255) NOT NULL,lastname VARCHAR(255) NOT NULL,PRIMARY KEY ( id ));");
		
		log.info("created db");
	}
}
