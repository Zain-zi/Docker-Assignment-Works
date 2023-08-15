package com.example.WebApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EnterDataController {
    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    GradesRepository gradesRepository;
    @Value("${analytics.service.base-url}")
    private String analyticsServiceBaseUrl;
    @Value("${results.service.base-url}")
    private String resultsServiceBaseUrl;
    @GetMapping("/enterData/{id}")
    public String showEnterDataPage(@PathVariable int id, Model model) {
        model.addAttribute("studentID", id);
        return "enterData";
    }
    @PostMapping("/enterGrades")
    public ModelAndView submitGrades(@RequestParam int studentID, @RequestParam int cppGrade, @RequestParam int pythonGrade, @RequestParam int javaGrade) {
        Grades grades = new Grades(studentID, cppGrade, pythonGrade, javaGrade);
        gradesRepository.saveAndFlush(grades);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Integer> request = new HttpEntity<>(studentID, headers);
        restTemplate.exchange(analyticsServiceBaseUrl + "/api/analytics", HttpMethod.POST, request, Void.class);
        return new ModelAndView("redirect:" + resultsServiceBaseUrl + "/" + studentID);
    }
}
