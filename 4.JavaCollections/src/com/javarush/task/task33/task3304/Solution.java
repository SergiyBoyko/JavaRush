package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Map;

/* 
Конвертация из одного класса в другой используя JSON
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Second s = (Second) convertOneToAnother(new First(), Second.class);
        First f = (First) convertOneToAnother(new Second(), First.class);
    }

    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {
        Object res = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String obj = mapper.writeValueAsString(one);

            ObjectReader reader = mapper.reader();
            JsonNode node = reader.readTree(obj);

            ObjectMapper mapper1 = new ObjectMapper();
            Map<String, Object> result = mapper1.convertValue(node, Map.class);
            String key1 = result.keySet().toArray()[0].toString(); //get first key
            ((ObjectNode)node).put(key1, resultClassObject.getSimpleName().toLowerCase()); // change key value
            result.put(key1, node.get(key1)); // return changed value
            res = mapper.convertValue(result, resultClassObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=First.class,  name="first"))
    public static class First {
        public int i;
        public String name;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=Second.class, name="second"))
    public static class Second {
        public int i;
        public String name;
    }
}
