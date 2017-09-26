package com.javarush.task.task33.task3305;

import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes.Type(value = RaceBike.class, name = "car")
public class Car extends Auto {
}