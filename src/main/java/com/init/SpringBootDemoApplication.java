package com.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.util.ApplicationEventListener;;


@SpringBootApplication
@ComponentScan(basePackages = {"com.*"})
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringBootDemoApplication.class);
		springApplication.addListeners(new ApplicationEventListener());
		springApplication.run(args);
		
	}
	

}
