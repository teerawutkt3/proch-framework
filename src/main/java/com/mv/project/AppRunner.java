package com.mv.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AppRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

	@Value("${timezone}")
	private String timezone;

	@Override
	public void run(String... args) throws Exception {
		//TODO Time Zone
		logger.info("==**== Spring boot application running in {} timezone : {}",timezone,new Date());
	}
}