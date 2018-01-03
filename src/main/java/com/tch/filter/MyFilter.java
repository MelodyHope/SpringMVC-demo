package com.tch.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

@Service
public class MyFilter extends GenericFilterBean {

	private final static Logger log = LoggerFactory.getLogger(MyFilter.class);

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO 自动生成的方法存根
		log.info("进入MyFilter");
		chain.doFilter(request, response);
		log.info("离开MyFilter");
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.filter.GenericFilterBean#initFilterBean()
	 */
	@Override
	protected void initFilterBean() throws ServletException {
		// TODO 自动生成的方法存根
		super.initFilterBean();
		System.out.println("MyFilter的Spring容器初始化方法！");
	}

}

