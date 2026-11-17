package com.example.demoapi.todo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class TodoServiceTest {

	private final TodoService service = new TodoService();

	@Test
	void shouldReturnPreloadedTodos() {
		List<Todo> todos = service.findAll();
		assertThat(todos).hasSize(3);
		assertThat(todos.get(0).title()).isEqualTo("学习 Spring Boot");
	}

	@Test
	void shouldCreateTodoWithIncrementedId() {
		int before = service.findAll().size();

		Todo created = service.create("写文档");

		assertThat(created.id()).isEqualTo(4);
		assertThat(created.completed()).isFalse();
		assertThat(service.findAll()).hasSize(before + 1);
	}

}
