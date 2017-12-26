package com.tch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.tch.bean.Student;
import com.tch.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {
	
	private static final String INSERT_STUDENT = "insert into student(name,age) values (?,?)";
	
	private static final String UPDATE_STUDENT = "update student set age = ? where name = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addStudent(Student student) {
		// TODO 自动生成的方法存根
		System.out.println("-->StudentServiceImpl.addStudent()");
		System.out.println(student);
		jdbcTemplate.update(INSERT_STUDENT, student.getName(),student.getAge());
		System.out.println("add student success!");
	}

	public void updateStudent(Student student) {
		// TODO 自动生成的方法存根
		jdbcTemplate.update(UPDATE_STUDENT, student.getAge(),student.getName());
		System.out.println("update student success!");
	}
	

}

