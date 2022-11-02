package com.example.preassignment.careerservice.services;

import com.example.preassignment.careerservice.CacheClient;
import com.example.preassignment.careerservice.dtos.JobDto;
import com.example.preassignment.careerservice.entities.Job;
import com.example.preassignment.careerservice.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.example.preassignment.careerservice.JobUtils.toDto;
import static com.example.preassignment.careerservice.JobUtils.toJobEntity;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CacheClient cacheClient;

    public Mono<JobDto> getJobById(String id) {
        JobDto job = cacheClient.get(id);
        if (job != null) {
            System.out.println("Getting job from cache");
            return Mono.just(job);
        }
        System.out.println("Getting job from db");
        return jobRepository.findByKeyId(id)
                .map(p -> {JobDto dto = toDto(p, null);
                        cacheClient.put(id, dto);
                return dto;
                    });
    }

    public Mono<JobDto> createJob(JobDto jobDto) {

        return Mono.just(jobDto)
                .flatMap(emp -> {
                    Job jobEntity = toJobEntity(emp);

                    Mono<Boolean> monoExists = jobRepository.existsById(jobEntity.getKey());
                    Mono<Job> monoJob =  jobRepository.save(jobEntity);
                    return Mono.zip( monoExists, monoJob);})
                .flatMap(result -> {
                    JobDto responseDto = null;
                    if(result.getT1()) {
                        responseDto = toDto(result.getT2(), "ALREADY_EXISTS");
                    } else {
                        responseDto = toDto(result.getT2(), "CREATED");
                    }
                    return Mono.just(responseDto);});


    }
}
