package com.example.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class AnalyticsService {
    GradesRepository gradesRepository;
    AnalyticsRepository analyticsRepository;

    @Autowired
    public AnalyticsService(GradesRepository gradesRepository, AnalyticsRepository analyticsRepository) {
        this.gradesRepository = gradesRepository;
        this.analyticsRepository = analyticsRepository;
    }
    public void calculateAnalytics(int studentID) {
        analyticsRepository.save(getAnalytics(studentID));
    }

    private Analytics getAnalytics(int studentID) {
        Grades grades = gradesRepository.findGradesByStudentID(studentID);
        return new Analytics(grades.getStudentID(), calculateAvg(grades), calculateMedian(grades), calculateMax(grades), calculateMin(grades));
    }

    private int calculateMin(Grades grades) {
        int[] gradesList = new int[]{grades.getCppGrade(), grades.getPythonGrade(), grades.getJavaGrade()};
        Arrays.sort(gradesList);
        return gradesList[0];
    }

    private int calculateMax(Grades grades) {
        int[] gradesList = new int[]{grades.getCppGrade(), grades.getPythonGrade(), grades.getJavaGrade()};
        Arrays.sort(gradesList);
        return gradesList[2];
    }

    private int calculateMedian(Grades grades) {
        int[] gradesList = new int[]{grades.getCppGrade(), grades.getPythonGrade(), grades.getJavaGrade()};
        Arrays.sort(gradesList);
        return gradesList[1];
    }

    private int calculateAvg(Grades grades) {
        return (grades.getCppGrade() + grades.getPythonGrade() + grades.getJavaGrade()) / 3;
    }


}
