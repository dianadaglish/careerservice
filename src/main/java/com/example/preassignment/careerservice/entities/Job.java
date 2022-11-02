package com.example.preassignment.careerservice.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @PrimaryKey
    private JobKey key;

    @Column("jod_name")
    private String name;

}
