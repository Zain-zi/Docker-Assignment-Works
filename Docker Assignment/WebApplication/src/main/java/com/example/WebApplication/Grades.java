package com.example.WebApplication;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Grades")
public class Grades {
    @Id
    @Column(name = "user_id")
    private int studentID;
    @Column(name = "cpp_grade")
    private int cppGrade;
    @Column(name = "java_grade")
    private int javaGrade;
    @Column(name = "python_grade")
    private int pythonGrade;
}
