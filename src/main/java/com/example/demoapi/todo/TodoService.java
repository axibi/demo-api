package com.example.demoapi.todo;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

/**
 * 基于内存的待办事项服务，重启后数据会丢失，仅用于演示。
 */
@Service
public class TodoService {

	private final AtomicLong sequence = new AtomicLong(0);

	private final List<Todo> todos = new CopyOnWriteArrayList<>();

	public TodoService() {
		create("学习 Spring Boot");
		create("编写三个 REST 接口");
		create("把仓库推送到 GitHub");
	}

	public List<Todo> findAll() {
		return List.copyOf(todos);
	}

	public Todo create(String title) {
		Todo todo = new Todo(sequence.incrementAndGet(), title, false);
		todos.add(todo);
		return todo;
	}

}
