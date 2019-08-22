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

import com.qa.todo.dto.ListDto;
import com.qa.todo.service.ListService;

@RestController
@RequestMapping("/list")
public class ListController {

	private ListService service;

	public ListController(ListService service) {
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<ListDto> createList(@RequestBody ListDto list) {
		return ResponseEntity.accepted().body(this.service.addList(list));
	}

	@GetMapping("get/{id}")
	public ResponseEntity<ListDto> getList(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.getList(id));
	}

	@PutMapping("update/{id}")
	public ResponseEntity<ListDto> updateList(@PathVariable Long id, @RequestBody ListDto list) {
		return ResponseEntity.accepted().body(this.service.updateList(list, id));
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Boolean> deleteList(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.removeList(id));
	}

}
