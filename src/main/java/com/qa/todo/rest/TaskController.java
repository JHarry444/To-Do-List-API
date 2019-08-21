package com.qa.todo.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.todo.dto.TaskDto;
import com.qa.todo.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

	private TaskService service;

	public TaskController(TaskService service) {
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto task) {
		return ResponseEntity.accepted().body(this.service.addTask(task));
	}

	@GetMapping("get/{id}")
	public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.getTask(id));
	}

	@PutMapping("update/{id}")
	public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto task) {
		return ResponseEntity.accepted().body(this.service.updateTask(task, id));
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Boolean> deleteTask(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.removeTask(id));
	}

}
