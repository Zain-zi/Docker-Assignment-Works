package com.example.ResultsService;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResultsRepository extends MongoRepository<Analytics, Integer> {
    Analytics findAllByStudentID(int studentID);
}
