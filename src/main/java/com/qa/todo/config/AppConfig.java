package com.qa.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qa.todo.util.ListMapper;
import com.qa.todo.util.TaskMapper;

@Configuration
public class AppConfig {

	@Bean
	public TaskMapper listMapper() {
		return new TaskMapper();
	}

	@Bean
	public ListMapper taskMapper() {
		return new ListMapper();
	}
}
