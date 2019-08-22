package com.qa.todo.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

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
import com.qa.todo.dto.ListDto;
import com.qa.todo.service.ListService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ListController.class)
public class ListControllerUnitTest {

	private final ListDto LIST_DTO = new ListDto(null, "Shopping", Collections.emptySet());

	@Autowired
	private MockMvc mock;

	@MockBean
	private ListService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testCreateList() throws Exception {
		this.mock.perform(post("/list/create").contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(this.LIST_DTO))).andExpect(status().isAccepted());
	}

	@Test
	public void testDtoReceivedForCreation() throws Exception {

		this.mock.perform(post("/list/create").contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(this.LIST_DTO)));

		ArgumentCaptor<ListDto> list = ArgumentCaptor.forClass(ListDto.class);
		verify(this.service, times(1)).addList(list.capture());
		assertThat(list.getValue()).isEqualTo(LIST_DTO);
	}

	@Test
	public void testGetList() throws Exception {
		this.mock.perform(get("/list/get/1")).andExpect(status().isOk());
	}

	@Test
	public void testIDReceivedForReading() throws Exception {
		final long id = 1;
		this.mock.perform(get("/list/get/" + id)).andExpect(status().isOk());

		ArgumentCaptor<Long> idCaptured = ArgumentCaptor.forClass(Long.class);
		verify(this.service, times(1)).getList(idCaptured.capture());
		assertThat(idCaptured.getValue()).isEqualTo(id);
	}

	@Test
	public void testUpdateList() throws Exception {
		this.mock.perform(put("/list/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(this.LIST_DTO))).andExpect(status().isAccepted());
	}

	@Test
	public void testID_DtoReceivedForUpdating() throws Exception {
		final long id = 1;
		this.mock.perform(put("/list/update/" + id).contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(this.LIST_DTO))).andExpect(status().isAccepted());

		ArgumentCaptor<Long> idCaptured = ArgumentCaptor.forClass(Long.class);
		ArgumentCaptor<ListDto> dtoCaptured = ArgumentCaptor.forClass(ListDto.class);
		verify(this.service, times(1)).updateList(dtoCaptured.capture(), idCaptured.capture());
		assertThat(idCaptured.getValue()).isEqualTo(id);
		assertThat(dtoCaptured.getValue()).isEqualTo(this.LIST_DTO);
	}

	@Test
	public void testDeleteList() throws Exception {
		this.mock.perform(delete("/list/delete/1")).andExpect(status().isOk());
	}

	@Test
	public void testIDReceivedForDeletion() throws Exception {
		final long id = 1;
		this.mock.perform(delete("/list/delete/" + id)).andExpect(status().isOk());

		ArgumentCaptor<Long> idCaptured = ArgumentCaptor.forClass(Long.class);
		verify(this.service, times(1)).removeList(idCaptured.capture());
		assertThat(idCaptured.getValue()).isEqualTo(id);
	}
}
