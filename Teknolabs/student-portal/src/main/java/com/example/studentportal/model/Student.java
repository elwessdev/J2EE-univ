package com.example.studentportal.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Student {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 16, message = "Age must be at least 16")
    private Integer age;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Course is required")
    private String course;

    @Pattern(regexp = "^[A-Z0-9]{8}$", message = "Student ID must be 8 characters with uppercase letters and numbers only")
    private String studentId;

    // Default constructor
    public Student() {
    }

    // Constructor with fields
    public Student(Long id, String name, Integer age, String email, String course, String studentId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
        this.studentId = studentId;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', age=" + age +
                ", email='" + email + "', course='" + course + "', studentId='" + studentId + "'}";
    }
}