package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.File;
import java.io.IOException;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        T obj = mapper.readValue(new File(fileName), clazz);
        return obj;
    }

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Cat obj = new Cat();
        obj.name = "Marmot";
        obj.age = 3;
        obj.weight = 4;

//Object to JSON in file
        mapper.writeValue(new File("C:/z/33L/json.json"), obj);

//Object to JSON in String
        String jsonInString = mapper.writeValueAsString(obj);
        System.out.println(jsonInString);

        Cat cat = convertFromJsonToNormal("C:/z/33L/json2.json", Cat.class);
        System.out.println(cat.name + " " + cat.age);
    }

    @JsonPropertyOrder({ "name", "age", "weight" })
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    static class Cat {
        @JsonDeserialize
        @JsonProperty("wildAnimal")
        String name;
        @JsonDeserialize
        @JsonProperty("over")
        int age;
        @JsonDeserialize
        int weight;
    }
}
