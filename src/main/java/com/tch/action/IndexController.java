package com.tch.action;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tch.bean.Student;
import com.tch.bean.Teacher;
import com.tch.service.IStudentService;
import com.tch.service.impl.StudentServiceImpl;

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
		IStudentService studentServiceProxy = (IStudentService)Proxy.newProxyInstance(StudentServiceImpl.class.getClassLoader(), new Class[]{IStudentService.class}, 
				new InvocationHandler() {
					
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						// TODO 自动生成的方法存根
						if("addStudent".equals(method.getName())){
							System.out.println("-->via proxy");
							method.invoke(studentService, args);
						}
						return null;
					}
				});
		studentServiceProxy.addStudent(student);
		return "index";
	}
	
	@RequestMapping("/add")
	public String addStudent(ModelMap model,
			@RequestParam(value="name",defaultValue="tom")String name,
			@RequestParam(value="age",defaultValue="20")String age){
		student.setName(name);
		student.setAge(Integer.parseInt(age));
		studentService.addStudent(student);
		model.put("student", student);
		return "index";
	}
	
	@RequestMapping("/update/{name}/{age}")
	public String updateStudent(@PathVariable("name")String name,
			@PathVariable("age")String age){
		student.setName(name);
		student.setAge(Integer.parseInt(age));
		studentService.updateStudent(student);
		return "index";
	}
	
}

