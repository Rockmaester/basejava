package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException {
        Resume resume = new Resume("01");
        Field field = resume.getClass().getDeclaredFields()[0];
        System.out.println("field.getName(): " + field.getName());

        field.setAccessible(true);
        System.out.println("field.get(resume): " + field.get(resume));

        field.set(resume, "02");
        System.out.println("field.get(resume): " + field.get(resume));

        // TODO: invoke resume.toString via reflection

        Method[] methods = resume.getClass().getDeclaredMethods();
        Method expectedMethod = null;
        for(Method method : methods){
            if(method.getName().equals("toString")){
                expectedMethod = method;
            }
        }

        try {
            System.out.println(expectedMethod.invoke(resume));
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
