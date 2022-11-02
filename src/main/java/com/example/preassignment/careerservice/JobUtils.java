package com.example.preassignment.careerservice;

import com.example.preassignment.careerservice.dtos.JobDto;
import com.example.preassignment.careerservice.entities.Job;
import com.example.preassignment.careerservice.entities.JobKey;
import org.springframework.beans.BeanUtils;

import javax.print.attribute.standard.JobPriority;

public class JobUtils {
    public static JobDto toDto(Job job, String status) {
        JobDto dto = new JobDto();
        dto.setId(job.getKey().getId());
        dto.setJavaExperience(job.getKey().getJavaExperience());
        dto.setSpringExperience(job.getKey().getSpringExperience());
        dto.setName(job.getName());
        dto.setStatus(status);
        return dto;
    }

    public static Job toJobEntity(JobDto dto) {
        JobKey pk = new JobKey();
        Job job = new Job();
        BeanUtils.copyProperties(dto, pk);
        job.setKey(pk);
        job.setName(dto.getName());
        return job;
    }
}
