package com.apps.examlab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String isbn;
    private String title;
    private String author;
}
