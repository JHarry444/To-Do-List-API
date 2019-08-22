package com.qa.todo.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.todo.dto.TaskDto;
import com.qa.todo.service.TaskService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TaskController.class)
public class TaskControllerUnitTest {

	private final TaskDto TASK_DTO = new TaskDto(1L, "Walk dog", false, 1L);

	@Autowired
	private MockMvc mock;

	@MockBean
	private TaskService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testCreateList() throws Exception {
		this.mock.perform(post("/task/create").contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(this.TASK_DTO))).andExpect(status().isAccepted());
	}

	@Test
	public void testDtoReceivedForCreation() throws Exception {

		this.mock.perform(post("/task/create").contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(this.TASK_DTO)));

		ArgumentCaptor<TaskDto> task = ArgumentCaptor.forClass(TaskDto.class);
		verify(this.service, times(1)).addTask(task.capture());
		assertThat(task.getValue()).isEqualTo(TASK_DTO);
	}

	@Test
	public void testGetList() throws Exception {
		this.mock.perform(get("/task/get/1")).andExpect(status().isOk());
	}

	@Test
	public void testIDReceivedForReading() throws Exception {
		final long id = 1;
		this.mock.perform(get("/task/get/" + id)).andExpect(status().isOk());

		ArgumentCaptor<Long> idCaptured = ArgumentCaptor.forClass(Long.class);
		verify(this.service, times(1)).getTask(idCaptured.capture());
		assertThat(idCaptured.getValue()).isEqualTo(id);
	}

	@Test
	public void testUpdateList() throws Exception {
		this.mock.perform(put("/task/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(this.TASK_DTO))).andExpect(status().isAccepted());
	}

	@Test
	public void testID_DtoReceivedForUpdating() throws Exception {
		final long id = 1;
		this.mock.perform(put("/task/update/" + id).contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(this.TASK_DTO))).andExpect(status().isAccepted());

		ArgumentCaptor<Long> idCaptured = ArgumentCaptor.forClass(Long.class);
		ArgumentCaptor<TaskDto> dtoCaptured = ArgumentCaptor.forClass(TaskDto.class);
		verify(this.service, times(1)).updateTask(dtoCaptured.capture(), idCaptured.capture());
		assertThat(idCaptured.getValue()).isEqualTo(id);
		assertThat(dtoCaptured.getValue()).isEqualTo(this.TASK_DTO);
	}

	@Test
	public void testDeleteList() throws Exception {
		this.mock.perform(delete("/task/delete/1")).andExpect(status().isOk());
	}

	@Test
	public void testIDReceivedForDeletion() throws Exception {
		final long id = 1;
		this.mock.perform(delete("/task/delete/" + id)).andExpect(status().isOk());

		ArgumentCaptor<Long> idCaptured = ArgumentCaptor.forClass(Long.class);
		verify(this.service, times(1)).removeTask(idCaptured.capture());
		assertThat(idCaptured.getValue()).isEqualTo(id);
	}
}
