package com.tch.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tch.bean.Student;
import com.tch.mybatis.mapper.StudentMapper;
import com.tch.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {
	
	private static final String INSERT_STUDENT = "insert into student(name,age) values (?,?)";
	
	private static final String UPDATE_STUDENT = "update student set age = ? where name = ?";
	
	private static final String DELETE_STUDENT = "delete from student where name = ? or age = ?";
	
	private static final String SELECT_STUDENT_SINGLE = "select * from student where name = ?";
	
	private static final String SELECT_STUDENT_ALL = "select * from student";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private StudentMapper studentMapper;
	
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
	
	@Override
	public void deleteStudent(Student student){
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = dataSource.getConnection();
			stat = conn.prepareStatement(DELETE_STUDENT);
			//特别注意：这个索引是从1开始的，不是0
			//stat.setString(0, student.getName());
			//stat.setInt(1, student.getAge());
			stat.setString(1, student.getName());
			stat.setInt(2, student.getAge());
			stat.execute();
		} catch (SQLException e) {
			// TODO: handle exception
		}finally{
			try {
				if(conn!=null)conn.close();
				if(stat!=null)stat.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
	}
	
	@Override
	public List<Student> queryStudents() {
		List<Student> students = jdbcTemplate.query(SELECT_STUDENT_ALL, new BeanPropertyRowMapper<>(Student.class));
		return students;
	}
	
	@Override
	public Student queryStudentWithName(String name) {
		Student student = jdbcTemplate.queryForObject(SELECT_STUDENT_SINGLE, new BeanPropertyRowMapper<>(Student.class), name);
		return student;
	}
	
	@Override
	@Transactional
	public Student queryStudentWithNameMybatis(String name) {
		Student student = studentMapper.getStudent(name);
		return student;
	}
	
	@Override
	public void addStudentMybatis(Student student) {
		studentMapper.insertStudent(student);
	}

}

