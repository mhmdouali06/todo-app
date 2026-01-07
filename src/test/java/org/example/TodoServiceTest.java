package org.example;



import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TodoServiceTest {

    private TodoService todoService;

    @Before
    public void setUp() {
        todoService = new TodoService();
    }

    @Test
    public void testAddTodo() {
        Todo todo = new Todo(1L, "Learn JUnit 4", false);

        todoService.addTodo(todo);

        assertEquals(1, todoService.getAllTodos().size());
        assertEquals("Learn JUnit 4", todoService.getAllTodos().get(0).getTitle());
        assertFalse(todoService.getAllTodos().get(0).isCompleted());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTodoWithNullTitle() {
        Todo todo = new Todo(1L, null, false);
        todoService.addTodo(todo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTodoWithEmptyTitle() {
        Todo todo = new Todo(1L, "   ", false);
        todoService.addTodo(todo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTodoWithDuplicateId() {
        Todo todo1 = new Todo(1L, "First Todo", false);
        Todo todo2 = new Todo(1L, "Duplicate Todo", false);

        todoService.addTodo(todo1);
        todoService.addTodo(todo2);
    }

    @Test
    public void testGetAllTodos() {
        todoService.addTodo(new Todo(1L, "Todo 1", false));
        todoService.addTodo(new Todo(2L, "Todo 2", false));

        List<Todo> todos = todoService.getAllTodos();

        assertEquals(2, todos.size());
    }

    @Test
    public void testCompleteTodo() {
        Todo todo = new Todo(1L, "Finish project", false);
        todoService.addTodo(todo);

        todoService.completeTodo(1L);

        assertTrue(todo.isCompleted());
    }

    @Test(expected = IllegalStateException.class)
    public void testCompleteTodoAlreadyCompleted() {
        Todo todo = new Todo(1L, "Already done", false);
        todoService.addTodo(todo);

        todoService.completeTodo(1L);
        todoService.completeTodo(1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompleteTodoNotFound() {
        todoService.completeTodo(99L);
    }
}
