package com.example.AnalyticsService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradesRepository extends JpaRepository<Grades, Integer> {
    Grades findGradesByStudentID(int studentID);
}
