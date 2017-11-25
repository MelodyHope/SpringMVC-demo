package com.tch.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Teacher {

	private final static Logger log = LoggerFactory.getLogger(Teacher.class);
	
	private Student student;
	
	public Teacher(Student student) {
		// TODO 自动生成的构造函数存根
		this.student = student;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Teacher [student=" + student + "] -->name:"+student.getName()+",age:"+student.getAge();
	}

}

