package com.apps.todoapp.controller;

import com.apps.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final TodoService todoService;

    @Autowired
    public HomeController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("tasks", todoService.getAllTasks());
        return "index";
    }
}
