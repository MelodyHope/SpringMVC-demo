package com.tch.service;

import java.util.List;

import com.tch.bean.Student;


public interface IStudentService {
	void addStudent(Student student);

	void updateStudent(Student student);

	void deleteStudent(Student student);

	List<Student> queryStudents();

	Student queryStudentWithName(String name);
}

