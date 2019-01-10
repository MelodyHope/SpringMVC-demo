package com.tch.test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.tch.bean.Student;
import com.tch.mybatis.mapper.StudentMapper;

public class MybatisTest_noSpring {
	
	private final static SqlSessionFactory sqlSessionFactory;
	
	static {
		String resource = "mybatisconfig-nospring.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			Student student = studentMapper.getStudent("Tom");
			System.out.println(student);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			sqlSession.close();
		}
	}

}
