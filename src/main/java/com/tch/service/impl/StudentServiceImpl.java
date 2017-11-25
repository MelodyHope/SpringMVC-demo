package com.tch.service.impl;

import org.springframework.stereotype.Service;

import com.tch.bean.Student;
import com.tch.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	public void addStudent(Student student) {
		// TODO 自动生成的方法存根
		System.out.println("-->StudentServiceImpl.addStudent()");
		System.out.println(student);
	}

}

