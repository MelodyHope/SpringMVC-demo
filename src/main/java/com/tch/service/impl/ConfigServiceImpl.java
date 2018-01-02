package com.tch.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl {

	private final static Logger log = LoggerFactory
			.getLogger(ConfigServiceImpl.class);
	
	@Value("${jdbc.url}")
	private String jdbc_url;

	public String getJdbc_url() {
		return jdbc_url;
	}

}

