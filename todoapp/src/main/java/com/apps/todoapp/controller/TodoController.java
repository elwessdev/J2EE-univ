package com.apps.todoapp.controller;

import com.apps.todoapp.model.Todo;
import com.apps.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTasks(){
        return ResponseEntity.ok(todoService.getAllTasks());
    }

    @PostMapping
    public ResponseEntity<String> addTask(@RequestBody Todo todo){
        todoService.addTask(todo);
        return ResponseEntity.ok("Task Added Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editTaskById(@PathVariable Long id, @RequestBody Todo todo){
        todoService.editTodoById(id,todo);
        return ResponseEntity.ok("Task Edited Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        todoService.deleteTodoById(id);
        return ResponseEntity.ok("Task Deleted Successfully");
//        return ResponseEntity.ok("Task Deleted Successfully");
//        return "redirect:/";
    }
}
