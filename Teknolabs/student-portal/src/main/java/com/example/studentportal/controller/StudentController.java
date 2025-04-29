package com.example.studentportal.controller;

import com.example.studentportal.model.Student;
import com.example.studentportal.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "index";
    }

    @GetMapping("/students")
    public String listAllStudents(Model model, @RequestParam(defaultValue = "all") String show){
        List<Student> students = studentService.getAllStudents();
        if(!show.equals("all")){
            students = students.subList(0, Integer.parseInt(show));
        }
        model.addAttribute("totalItems", students.toArray().length);
        model.addAttribute("sorted", false);
        model.addAttribute("students", students);
        return "students/list";
    }

    // Filter By Name
    @GetMapping("/studentsFilter")
    public String getAllStudents(Model model, @RequestParam(required = false) String nameFilter) {
        List<Student> students = studentService.getAllStudents();
        List<Student> filterList = new ArrayList<>();

        if (nameFilter != null && !nameFilter.isEmpty()) {
            for (Student st:students){
                if(st.getName().toLowerCase().contains(nameFilter.toLowerCase())){
                    filterList.add(st);
                }
            }
            model.addAttribute("nameFilter", nameFilter);
        } else {
            filterList = studentService.getAllStudents();
        }

        model.addAttribute("students", filterList);
        return "students/list";
    }

    // Sort By Age
    @GetMapping("/sortByAge")
    public String sortStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        students.sort(Comparator.comparing(Student::getAge));
        model.addAttribute("sorted", true);
        model.addAttribute("students", students);
        return "students/list";
    }


    @GetMapping("/students/{id}")
    public String viewStudent(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
        model.addAttribute("student", student);
        return "students/view";
    }

    @GetMapping("/students/new")
    public String showNewStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    @PostMapping("/students/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "students/form";
        }

        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("successMessage", "Student saved successfully!");
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
        model.addAttribute("student", student);
        return "students/form";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        studentService.deleteStudent(id);
        redirectAttributes.addFlashAttribute("successMessage", "Student deleted successfully!");
        return "redirect:/students";
    }
}