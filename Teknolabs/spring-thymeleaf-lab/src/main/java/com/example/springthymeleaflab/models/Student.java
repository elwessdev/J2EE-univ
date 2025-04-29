package com.example.springthymeleaflab.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Student {

    private static long idCounter = 0;
    private long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Major is required")
    private String major;

    // Default constructor
    public Student() {}

    // Constructor with fields
    public Student(String name, String email, String major) {
        this.name = name;
        this.email = email;
        this.major = major;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public long getId(){
        return id;
    }

    public void assignId() {
        this.id = idCounter;
        idCounter++;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id: " + id + '\'' +
                ", name: '" + name + '\'' +
                ", email: '" + email + '\'' +
                ", major: '" + major + '\'' +
                '}';
    }
}