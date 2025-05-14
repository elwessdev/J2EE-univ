package com.apps.todoapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@ToString
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String description;
    private Boolean isCompleted = false;

    public Todo(){}

    public Todo(Long id, String description, Boolean isCompleted){
        this.id = id;
        this.description = description;
        this.isCompleted = isCompleted;
    }
}
