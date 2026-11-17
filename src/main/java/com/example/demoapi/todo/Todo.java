package com.example.demoapi.todo;

/**
 * 待办事项模型。
 *
 * @param id        自增主键
 * @param title     待办标题
 * @param completed 是否已完成
 */
public record Todo(long id, String title, boolean completed) {
}
