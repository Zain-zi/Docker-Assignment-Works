package com.example.ResultsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultsService {
    ResultsRepository resultsRepository;
    @Autowired
    public ResultsService(ResultsRepository resultsRepository) {
        this.resultsRepository = resultsRepository;
    }

    public Analytics getResults(int studentID) {
        return resultsRepository.findAllByStudentID(studentID);
    }
}
