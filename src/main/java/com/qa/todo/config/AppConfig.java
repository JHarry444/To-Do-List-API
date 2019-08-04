package com.qa.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qa.todo.util.BaseMapper;

@Configuration
public class AppConfig {

	@Bean
	public BaseMapper beanMapper() {
		return new BaseMapper();
	}

}
