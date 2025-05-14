package com.apps.todoapp.service;

import com.apps.todoapp.model.Todo;
import com.apps.todoapp.repository.TodoRepository;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // Get Tasks
    @Cacheable("tasks")
    public List<Todo> getAllTasks(){
        return todoRepository.findAll();
    }

    // Add Task
    @CacheEvict(value = "tasks", allEntries = true)
    public void addTask(Todo todo){
        if (todoRepository.save(todo) == null) {
            throw new IllegalArgumentException("Something wrong when adding todo");
        }
    }

    // Update Task
    @CacheEvict(value = "tasks", allEntries = true)
    public void editTodoById(Long id, Todo todo){
        Todo data = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        data.setDescription(todo.getDescription());
        data.setIsCompleted(todo.getIsCompleted());
        todoRepository.save(data);
    }

    // Delete Task
    @CacheEvict(value = "tasks", allEntries = true)
    public void deleteTodoById(Long id){
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        todoRepository.delete(todo);
    }
}
