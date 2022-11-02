package com.example.preassignment.careerservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;


@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyClass
public class JobKey {
    @PrimaryKeyColumn(name = "job_id", type = PARTITIONED)
    private String id;

    @PrimaryKeyColumn(name = "java_exp")
    private double javaExperience;

    @PrimaryKeyColumn(name = "spring_exp")
    private double springExperience;
}
