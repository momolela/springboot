package com.momolela.entity;

import lombok.Data;

@Data
public class Dog {
    private String name;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
