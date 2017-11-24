package com.tch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tch.bean.Student;

@Configuration
public class TestConfig {

	@Bean
	public Student student(){
		System.out.println("-->via @Configuration");
		return new Student();//这里调用无参构造器，如果bean存在有参的构造器就会报错
	}
	
}

