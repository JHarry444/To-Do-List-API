package com.qa.todo.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.todo.dto.TaskDto;
import com.qa.todo.persistence.domain.ListEntity;
import com.qa.todo.persistence.domain.TaskEntity;
import com.qa.todo.persistence.repo.TaskRepo;
import com.qa.todo.util.BaseMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskServiceUnitTest {

	private final TaskDto Task_DTO_1 = new TaskDto(1L, "Build shed", false);

	private final ListEntity LIST_ENTITY = new ListEntity("To do");

	private final TaskEntity Task_ENTITY_1 = new TaskEntity("Build shed", false, this.LIST_ENTITY);

	private final TaskEntity Task_ENTITY_2 = new TaskEntity("Bloop", false, this.LIST_ENTITY);

	@InjectMocks
	private TaskService service;

	@Mock
	private TaskRepo repo;

	@Mock
	private BaseMapper mapper;

	@Test
	public void testServiceNotNull() {
		assertNotNull("Failed to create service", this.service);
	}

	@Test
	public void testAddTask() {
		TaskEntity expected = new TaskEntity(this.Task_ENTITY_1.getDescription(), this.Task_ENTITY_1.isCompleted(),
				this.Task_ENTITY_1.getList());
		when(this.mapper.map(Task_DTO_1, TaskEntity.class)).thenReturn(expected);
		TaskEntity expectedWithID = new TaskEntity(this.Task_ENTITY_1.getDescription(),
				this.Task_ENTITY_1.isCompleted(), this.Task_ENTITY_1.getList());
		expectedWithID.setId(1L);
		when(this.repo.save(this.Task_ENTITY_1)).thenReturn(expectedWithID);

		this.service.addTask(Task_DTO_1);

		verify(this.repo, times(1)).save(this.Task_ENTITY_1);
		verify(this.mapper, times(1)).map(Task_DTO_1, TaskEntity.class);
	}

	@Test
	public void testGetTask() {
		when(this.repo.findById(Mockito.anyLong())).thenReturn(Optional.of(this.Task_ENTITY_1));
		when(this.mapper.map(Task_ENTITY_1, TaskDto.class)).thenReturn(this.Task_DTO_1);

		long id = 1;
		this.service.getTask(id);

		verify(this.repo, times(1)).findById(id);
		verify(this.mapper, times(1)).map(Task_ENTITY_1, TaskDto.class);
	}

	@Test
	public void testRemoveTask() {
		final long ID = 1;
		when(this.repo.existsById(ID)).thenReturn(false);

		this.service.removeTask(ID);

		verify(this.repo, times(1)).deleteById(ID);
		verify(this.repo, times(1)).existsById(ID);
	}

	@Test
	public void testUpdateTask() {
		when(this.mapper.map(Task_DTO_1, TaskEntity.class)).thenReturn(this.Task_ENTITY_1);
		when(this.repo.findById(Mockito.anyLong())).thenReturn(Optional.of(this.Task_ENTITY_2));
		when(this.repo.save(this.Task_ENTITY_2)).thenReturn(this.Task_ENTITY_2);

		long id = 1;
		this.service.updateTask(Task_DTO_1, id);

		verify(this.repo, times(1)).findById(id);
		verify(this.mapper, times(1)).map(Task_DTO_1, TaskEntity.class);
		verify(this.repo, times(1)).save(this.Task_ENTITY_2);
	}

}
