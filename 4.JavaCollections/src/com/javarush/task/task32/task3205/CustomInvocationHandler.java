package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Serhii Boiko on 02.08.2017.
 */
public class CustomInvocationHandler implements InvocationHandler {
    SomeInterfaceWithMethods interfaceWithMethods;

    public CustomInvocationHandler(SomeInterfaceWithMethods interfaceWithMethods) {
        this.interfaceWithMethods = interfaceWithMethods;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        System.out.printf("%s in\n", method.getName());
        result = method.invoke(interfaceWithMethods, args);
        System.out.printf("%s out\n", method.getName());
        return result;
    }

}
