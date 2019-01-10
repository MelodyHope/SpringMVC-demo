package com.tch.mybatis.mapper;

import com.tch.bean.Student;

public interface StudentMapper {
	
	public void insertStudent(Student student);
	
	public Student getStudent(String name);

}
