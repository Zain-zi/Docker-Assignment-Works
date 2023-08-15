package com.example.ResultsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ResultsController {
    ResultsService resultsService;
    @Autowired
    public ResultsController(ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    @GetMapping("/results/{id}")
    public String showResults(@PathVariable int id, Model model) {
        Analytics results = resultsService.getResults(id);
        model.addAttribute("results", results);
        return "results";
    }
}
