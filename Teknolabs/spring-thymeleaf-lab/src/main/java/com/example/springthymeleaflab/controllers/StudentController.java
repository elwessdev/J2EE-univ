package com.example.springthymeleaflab.controllers;

import com.example.springthymeleaflab.models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final List<Student> students = new ArrayList<>();

    public List<Student> getStudents(){
        return students;
    }

    @GetMapping("/list")
    public String listStudents(Model model) {
        model.addAttribute("students", students);
        for(Student student : students) {
            System.out.println(student.getId());
        }
        return "students/list";
    }

    // Register
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/register";
    }
    @PostMapping("/register")
    public String registerStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "students/register";
        }
        student.assignId();
        students.add(student);
        return "redirect:/students/list";
    }

    // Edit
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Student student = findById(id);
        if (student == null) {
            return "redirect:/students/list";
        }
        model.addAttribute("student", student);
        return "students/edit"; // View: students/edit.html
    }
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") Student updatedStudent) {
        Student existing = findById(updatedStudent.getId());
        //System.out.println(updatedStudent);
        if (existing != null) {
            existing.setName(updatedStudent.getName());
            existing.setEmail(updatedStudent.getEmail());
            existing.setMajor(updatedStudent.getMajor());
        }
        return "redirect:/students/list";
    }

    // find student by ID
    private Student findById(long id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Delete user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        Student student = findById(id);
        if(student == null) {
            return "redirect:/students/list";
        }
        //System.out.println(student);
        students.remove(student);
        return "redirect:/students/list";
    }

    // Filter
    @GetMapping("/listFilter")
    public String listStudents(Model model, @RequestParam(required = false) String name, @RequestParam(required = false) String major) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            boolean nameMatches = (name == null || name.isEmpty() ||
                    student.getName().toLowerCase().contains(name.toLowerCase()));
            boolean majorMatches = (major == null || major.isEmpty() ||
                    student.getMajor().toLowerCase().contains(major.toLowerCase()));

            if (nameMatches && majorMatches) {
                filteredStudents.add(student);
            }
        }
        model.addAttribute("students", filteredStudents);
        return "students/list";
    }

}