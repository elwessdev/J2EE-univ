package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student updateStudent(Student student) {
        // First check if student exists
        if (studentRepository.existsById(student.getId())) {
            return studentRepository.save(student);
        }
        throw new RuntimeException("Student with ID " + student.getId() + " not found");
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudentsByMajor(String major) {
        return studentRepository.findByMajor(major);
    }

    @Override
    public List<Student> getStudentsYoungerThan(int age) {
        return studentRepository.findByAgeLessThan(age);
    }

    @Override
    public List<Student> getStudentsByLastNamePattern(String pattern) {
        return studentRepository.findByLastNameContaining(pattern);
    }

    @Override
    public List<Student> getStudentsInAgeRange(int minAge, int maxAge) {
        return studentRepository.findStudentsInAgeRange(minAge, maxAge);
    }
}