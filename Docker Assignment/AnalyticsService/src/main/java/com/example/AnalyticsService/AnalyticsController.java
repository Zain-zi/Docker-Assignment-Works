package com.example.AnalyticsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AnalyticsController {
    AnalyticsService analyticsService;
    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @PostMapping("/api/analytics")
    public ResponseEntity<Void> calculateAnalytics(@RequestBody int studentID) {
        analyticsService.calculateAnalytics(studentID);
        return ResponseEntity.ok().build();
    }
}
