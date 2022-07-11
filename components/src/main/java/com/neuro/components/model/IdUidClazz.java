package com.neuro.components.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class IdUidClazz {
    @Id
    @GeneratedValue(generator = "custom_generator")
    @GenericGenerator(name = "custom_generator", strategy = "com.neuro.components.generator.CustomIdUidGenerator",
            parameters ={

            })
    private Long id;

    private String uid;
}
