package com.tch.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tch.bean.Student;
import com.tch.bean.Teacher;
import com.tch.service.IStudentService;

@Controller
public class IndexController {
	
	@Autowired
	private Student student;
	
	@Autowired
	private Teacher teacher;
	
	@Autowired
	private IStudentService studentService;

	@RequestMapping("/index")
	public String list(HttpServletRequest request){
		System.out.println("name:-->"+student.getName()+",age:-->"+student.getAge());
		System.out.println(teacher);
		studentService.addStudent(student);
		return "index";
	}
	
}

