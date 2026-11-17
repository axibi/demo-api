package com.example.demoapi.todo;

/**
 * 创建待办事项的请求体。
 *
 * @param title 待办标题，必填
 */
public record CreateTodoRequest(String title) {
}
