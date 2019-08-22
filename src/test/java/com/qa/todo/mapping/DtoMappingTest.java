package com.qa.todo.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.todo.ToDoListApiApplication;
import com.qa.todo.config.AppConfig;
import com.qa.todo.dto.ListDto;
import com.qa.todo.dto.TaskDto;
import com.qa.todo.persistence.domain.ListEntity;
import com.qa.todo.persistence.domain.TaskEntity;
import com.qa.todo.persistence.repo.ListRepo;
import com.qa.todo.persistence.repo.TaskRepo;
import com.qa.todo.util.ListMapper;
import com.qa.todo.util.TaskMapper;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = { AppConfig.class, ToDoListApiApplication.class })
public class DtoMappingTest {

	@Autowired
	private TestEntityManager testEM;

	@Autowired
	private ListRepo listRepo;

	@Autowired
	private TaskRepo taskRepo;

	@Autowired
	private ListMapper listMapper;

	@Autowired
	private TaskMapper taskMapper;

	@Test
	public void ListDtoMappingTest() {
		// given
		ListEntity listEntity = new ListEntity("Testing");
		this.testEM.persist(listEntity);
		this.testEM.flush();
		ListDto listDto = new ListDto(listEntity.getId(), listEntity.getTitle(), Collections.emptySet());
		// when
		ListEntity found = this.listRepo.findById(listEntity.getId()).get();
		ListDto mapped = this.listMapper.mapToDto(found);
		// then
		assertThat(listDto).isEqualTo(mapped);
	}

	@Test
	public void TaskDtoMappingTest() {
		// given
		ListEntity listEntity = new ListEntity("Testing");
		this.testEM.persist(listEntity);
		this.testEM.flush();
		TaskEntity taskEntity = new TaskEntity("Write tests", false, listEntity);
		this.testEM.persist(taskEntity);
		this.testEM.flush();
		TaskDto taskDto = new TaskDto(taskEntity.getId(), taskEntity.getDescription(), taskEntity.isCompleted(),
				taskEntity.getList().getId());
		// when
		TaskEntity found = this.taskRepo.findById(taskEntity.getId()).get();
		TaskDto mapped = this.taskMapper.mapToDto(found);
		// then
		assertThat(taskDto).isEqualTo(mapped);
	}
}
