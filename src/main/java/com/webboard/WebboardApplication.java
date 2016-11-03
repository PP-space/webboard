package com.webboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class WebboardApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WebboardApplication.class, args);
	}
}
