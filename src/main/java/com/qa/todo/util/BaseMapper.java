package com.qa.todo.util;

import org.dozer.DozerBeanMapper;

public class BaseMapper {

	private DozerBeanMapper mapper;

	public BaseMapper() {
		super();
		this.mapper = new DozerBeanMapper();
	}

	public <T> T map(Object source, Class<T> targetClass) {
		return this.mapper.map(source, targetClass);
	}

}
