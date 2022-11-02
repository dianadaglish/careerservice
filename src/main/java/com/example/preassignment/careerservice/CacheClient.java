package com.example.preassignment.careerservice;

import com.example.preassignment.careerservice.dtos.JobDto;
import com.example.preassignment.careerservice.entities.Job;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.stereotype.Component;


@Component
public class CacheClient {

    public static final String JOBS = "jobs";
    private final HazelcastInstance hazelcastInstance
            = Hazelcast.newHazelcastInstance();

    public JobDto put(String jobId, JobDto job){
        IMap<String, JobDto> map = hazelcastInstance.getMap(JOBS);
        return map.putIfAbsent(jobId, job);
    }

    public JobDto get(String jobId){
        IMap<String, JobDto> map = hazelcastInstance.getMap(JOBS);
        return map.get(jobId);
    }


}