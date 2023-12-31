package com.example.dslab941.Core.Models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class Student implements Serializable {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String STUDENT = "Student";

    private int id;
    private int age;
    private String name;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}