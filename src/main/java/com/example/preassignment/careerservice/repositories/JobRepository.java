package com.example.preassignment.careerservice.repositories;

import com.example.preassignment.careerservice.entities.Job;
import com.example.preassignment.careerservice.entities.JobKey;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.stereotype.Repository;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface JobRepository extends ReactiveCassandraRepository<Job, JobKey> {

    @AllowFiltering
    Mono<Job> findByKeyId(final String id);
}