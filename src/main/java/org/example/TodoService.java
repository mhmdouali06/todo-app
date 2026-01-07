package org.example;

import java.util.ArrayList;
import java.util.List;

public class TodoService {
    public List<Todo> todoList=new ArrayList<>();
    public void addTodo(Todo todo) {
        todo.setCompleted(false);
        if (todo.getTitle() == null || todo.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty!");
        }

        boolean exists = todoList.stream()
                .anyMatch(t -> t.getId() == todo.getId());

        if (exists) {
            throw new IllegalArgumentException("Todo with ID " + todo.getId() + " already exists!");
        }

        todoList.add(todo);
    }
    public List<Todo> getAllTodos(){
        return todoList;
    }
    public void completeTodo(Long id) {
        Todo todo = todoList.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Todo with ID " + id + " not found!"));

        if (todo.isCompleted()) {
            throw new IllegalStateException("Todo with ID " + id + " is already completed!");
        }

        todo.setCompleted(true);
    }
}
