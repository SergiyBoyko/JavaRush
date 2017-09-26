package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

/**
 * Created by Serhii Boiko on 14.08.2017.
 */
public class FactoryProducer {

    public static enum HumanFactoryType {
        MALE,
        FEMALE
    }

    public static AbstractFactory getFactory(HumanFactoryType type) {
        if (type == HumanFactoryType.FEMALE) return new FemaleFactory();
        else if (type == HumanFactoryType.MALE) return new MaleFactory();
        else return null;
    }
}
