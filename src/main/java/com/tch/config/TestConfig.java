package com.tch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.tch.bean.Student;
import com.tch.bean.Teacher;
import com.tch.service.impl.StudentServiceImpl;
import com.tch.service.impl.TeacherServiceImpl;

//@ComponentScan(basePackages={"com.tch.action","com.tch.bean"})
//@ComponentScan(basePackages={"com.tch.bean","com.tch.service"})
//@ComponentScan(basePackageClasses={TeacherServiceImpl.class,StudentServiceImpl.class})
@Configuration
public class TestConfig {

	@Bean
	public Student student(){
		System.out.println("-->via @Configuration创建student实例");
//		return new Student();//这里调用无参构造器，如果bean存在有参的构造器就会报错
		return new Student("Tom",20);
	}
	
	@Bean
	public Teacher teacher(){
		System.out.println("-->在teacher中注入student");
		return new Teacher(student());
	}
	
}

