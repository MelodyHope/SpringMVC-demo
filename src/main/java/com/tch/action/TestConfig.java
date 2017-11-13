package com.tch.action;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tch.bean.Student;

@Configuration
public class TestConfig {

	@Bean
	public Student student(){
		System.out.println("-->via @Configuration");
		return new Student();
	}
	
}

