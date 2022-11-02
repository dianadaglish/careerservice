package com.example.preassignment.careerservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDto implements Serializable {

        @JsonProperty("job_id")
        private String id;
        @JsonProperty("job_name")
        private String name;
        @JsonProperty("java_exp")
        private double javaExperience;
        @JsonProperty("spring_exp")
        private double springExperience;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String status;
}
