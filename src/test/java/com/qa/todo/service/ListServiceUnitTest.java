package com.qa.todo.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.todo.dto.ListDto;
import com.qa.todo.persistence.domain.ListEntity;
import com.qa.todo.persistence.repo.ListRepo;
import com.qa.todo.util.BaseMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ListServiceUnitTest {

	private final ListDto LIST_DTO_1 = new ListDto(1L, "To do", Collections.emptySet());

	private final ListEntity LIST_ENTITY_1 = new ListEntity("To do");

	private final ListEntity LIST_ENTITY_2 = new ListEntity("Shooping list");

	@InjectMocks
	private ListService service;

	@Mock
	private ListRepo repo;

	@Mock
	private BaseMapper mapper;

	@Test
	public void testServiceNotNull() {
		assertNotNull("Failed to create service", this.service);
	}

	@Test
	public void testAddList() {
		ListEntity expected = new ListEntity(this.LIST_ENTITY_1.getTitle());
		when(this.mapper.map(LIST_DTO_1, ListEntity.class)).thenReturn(expected);
		ListEntity expectedWithID = new ListEntity(this.LIST_ENTITY_1.getTitle());
		expectedWithID.setId(1L);
		when(this.repo.save(this.LIST_ENTITY_1)).thenReturn(expectedWithID);

		this.service.addList(LIST_DTO_1);

		verify(this.repo, times(1)).save(this.LIST_ENTITY_1);
		verify(this.mapper, times(1)).map(LIST_DTO_1, ListEntity.class);
	}

	@Test
	public void testGetList() {
		when(this.repo.findById(Mockito.anyLong())).thenReturn(Optional.of(this.LIST_ENTITY_1));
		when(this.mapper.map(LIST_ENTITY_1, ListDto.class)).thenReturn(this.LIST_DTO_1);

		long id = 1;
		this.service.getList(id);

		verify(this.repo, times(1)).findById(id);
		verify(this.mapper, times(1)).map(LIST_ENTITY_1, ListDto.class);
	}

	@Test
	public void testRemoveList() {
		final long ID = 1;
		when(this.repo.findById(ID)).thenReturn(Optional.of(this.LIST_ENTITY_1));

		this.service.removeList(ID);

		verify(this.repo, times(1)).findById(ID);
		verify(this.repo, times(1)).delete(this.LIST_ENTITY_1);
		verify(this.repo, times(1)).existsById(ID);
	}

	@Test
	public void testUpdateList() {
		when(this.mapper.map(LIST_DTO_1, ListEntity.class)).thenReturn(this.LIST_ENTITY_1);
		when(this.repo.findById(Mockito.anyLong())).thenReturn(Optional.of(this.LIST_ENTITY_2));
		when(this.repo.save(this.LIST_ENTITY_2)).thenReturn(this.LIST_ENTITY_2);

		long id = 1;
		this.service.updateList(LIST_DTO_1, id);

		verify(this.repo, times(1)).findById(id);
		verify(this.mapper, times(1)).map(LIST_DTO_1, ListEntity.class);
		verify(this.repo, times(1)).save(this.LIST_ENTITY_2);
	}

}
