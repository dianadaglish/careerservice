package com.example.preassignment.careerservice.controllers;

import com.example.preassignment.careerservice.dtos.EmployeeDto;
import com.example.preassignment.careerservice.dtos.JobDto;
import com.example.preassignment.careerservice.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping(value = "/createJobProfile", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Mono<JobDto> createJob(@RequestBody JobDto jobDto) {
        return jobService.createJob(jobDto);
    }

    @Autowired
    WebClient webClient;

    @PostMapping(value = "/findEmpForJobId", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Flux<EmployeeDto> findEmpByJob(@RequestBody JobDto jobDto) {
        Mono <JobDto> jobMono = jobService.getJobById(jobDto.getId());


        Flux<EmployeeDto> flux= Flux.from(jobMono)
                .flatMap(p -> { return webClient
                                    .post()
                                    .uri("/findEmpSkillset")
                                    .body(Mono.just(p), EmployeeDto.class)
                                    .retrieve()
                                    .bodyToFlux(EmployeeDto.class);
                });

        flux.subscribe(emp -> System.out.println("Emp name: " +emp.getName()));
        return flux;

    }

    @PostMapping(value = "/getJobProfileFromCache", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Mono<JobDto> getJobProfileFromCache(@RequestBody JobDto jobDto) {

        return jobService.getJobById(jobDto.getId());
    }
}
