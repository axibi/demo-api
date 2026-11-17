package com.example.demoapi.todo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示用的三个 REST 接口：
 * <ul>
 * <li>GET /api/hello —— 打招呼，可选 name 参数</li>
 * <li>GET /api/todos —— 查询待办列表</li>
 * <li>POST /api/todos —— 新增待办</li>
 * </ul>
 */
@RestController
@RequestMapping("/api")
public class TodoController {

	private final TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	/** 接口一：问候接口。 */
	@GetMapping("/hello")
	public Map<String, Object> hello(@RequestParam(defaultValue = "World") String name) {
		return Map.of(
				"message", "Hello, " + name + "!",
				"service", "demo-api",
				"timestamp", LocalDateTime.now().toString());
	}

	/** 接口二：查询全部待办。 */
	@GetMapping("/todos")
	public Map<String, Object> listTodos() {
		List<Todo> todos = todoService.findAll();
		return Map.of(
				"total", todos.size(),
				"items", todos);
	}

	/** 接口三：新增待办。 */
	@PostMapping("/todos")
	public ResponseEntity<?> createTodo(@RequestBody(required = false) CreateTodoRequest request) {
		if (request == null || request.title() == null || request.title().isBlank()) {
			return ResponseEntity.badRequest().body(Map.of("error", "title 不能为空"));
		}
		Todo created = todoService.create(request.title().trim());
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

}
