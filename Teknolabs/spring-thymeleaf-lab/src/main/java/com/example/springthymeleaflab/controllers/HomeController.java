package com.example.springthymeleaflab.controllers;

import com.example.springthymeleaflab.models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final StudentController studentController;

    public HomeController(StudentController studentController) {
        this.studentController = studentController;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Student> students = studentController.getStudents();
        Map<String, Long> majorStats = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getMajor,
                        Collectors.counting()
                ));
        model.addAttribute("majors", majorStats);
        return "home";
    }
}