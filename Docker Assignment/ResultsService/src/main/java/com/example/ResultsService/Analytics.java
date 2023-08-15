package com.example.ResultsService;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Document(collection = "analytics")
public class Analytics {
    @Id
    @Field("studentID")
    private int studentID;
    @Field("courseAvg")
    private double courseAvg;
    @Field("courseMedian")
    private double courseMedian;
    @Field("courseMax")
    private double courseMax;
    @Field("courseMin")
    private double courseMin;
}
