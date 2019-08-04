package com.qa.todo.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.todo.persistence.domain.ListEntity;
import com.qa.todo.persistence.repo.ListRepo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ListRepoIntegrationTest {

	@Autowired
	private TestEntityManager testEM;

	@Autowired
	private ListRepo repo;

	@Test
	public void whenFindById_thenReturnList() {
		// given
		ListEntity list = new ListEntity("Testing");
		this.testEM.persist(list);
		this.testEM.flush();
		// when
		ListEntity found = this.repo.findById(list.getId()).get();

		// then
		assertThat(found).isEqualTo(list);
	}

}
