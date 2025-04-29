package com.example.studentmanagement;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(StudentRepository repository) {
        return args -> {
            // Only add test data if repository is empty
            if (repository.count() == 0) {
                repository.save(new Student("John", "Doe", "john.doe@example.com", 20, "Computer Science"));
                repository.save(new Student("Jane", "Smith", "jane.smith@example.com", 22, "Mathematics"));
                repository.save(new Student("Alice", "Johnson", "alice.j@example.com", 19, "Computer Science"));
                repository.save(new Student("Bob", "Williams", "bob.w@example.com", 21, "Physics"));
                repository.save(new Student("Charlie", "Brown", "charlie.b@example.com", 23, "Mathematics"));
                System.out.println("Sample data initialized!");
            }
        };
    }
}