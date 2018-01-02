package com.tch.action;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tch.bean.Student;
import com.tch.bean.Teacher;
import com.tch.service.IStudentService;
import com.tch.service.impl.ConfigServiceImpl;
import com.tch.service.impl.StudentServiceImpl;

@Controller
public class IndexController {
	
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private Student student;
	
	@Autowired
	private Teacher teacher;
	
	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private ConfigServiceImpl configServiceImpl;
	
	//这里无法注入spring父容器的property，只能注入MVC容器的property
//	@Value("${jdbc.url}")
//	private String jdbc_url;
	
	@Value("${package.name1}")
	private String packagename1;
	
	@Value("${package.name2}")
	private String packagename2;

	@RequestMapping("/index")
	@Deprecated
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
	
	@RequestMapping("/idx")
	public String newlist(HttpServletRequest request){
//		System.out.println("-->注入的jdbc_url属性值为："+jdbc_url);
		System.out.println("通过注解@Service获取jdbc_url属性值为："+configServiceImpl.getJdbc_url());
		System.out.println("-->注入的package.name1属性值为："+packagename1);
		System.out.println("-->注入的package.name2属性值为："+packagename2);
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
	
	@RequestMapping("/delete")
	public String deleteStudent(String name,String age){
		student.setName(name);
		student.setAge(Integer.parseInt(age));
		studentService.deleteStudent(student);
		return "index";
	}
	
}

