package com.qa.todo.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.todo.persistence.domain.ListEntity;
import com.qa.todo.persistence.domain.TaskEntity;
import com.qa.todo.persistence.repo.TaskRepo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepoIntegrationTest {

	@Autowired
	private TestEntityManager testEM;

	@Autowired
	private TaskRepo taskRepo;

	@Test
	public void whenFindById_thenReturnTask() {
		// given
		ListEntity testList = new ListEntity("Testing");
		this.testEM.persist(testList);
		TaskEntity task = new TaskEntity("Write tests", false, testList);
		this.testEM.persist(task);
		this.testEM.flush();
		// when
		TaskEntity found = this.taskRepo.findById(task.getId()).get();
		// then
		assertThat(found).isEqualTo(task);
		assertThat(found.getList()).isEqualTo(testList);
	}

}
