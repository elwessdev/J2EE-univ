package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        try {
            Student updatedStudent = studentService.updateStudent(student);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable String major) {
        List<Student> students = studentService.getStudentsByMajor(major);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/younger-than/{age}")
    public ResponseEntity<List<Student>> getStudentsYoungerThan(@PathVariable int age) {
        List<Student> students = studentService.getStudentsYoungerThan(age);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/by-lastname/{pattern}")
    public ResponseEntity<List<Student>> getStudentsByLastNamePattern(@PathVariable String pattern) {
        List<Student> students = studentService.getStudentsByLastNamePattern(pattern);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/age-range")
    public ResponseEntity<List<Student>> getStudentsInAgeRange(
            @RequestParam int minAge, @RequestParam int maxAge) {
        List<Student> students = studentService.getStudentsInAgeRange(minAge, maxAge);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}