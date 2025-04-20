package com.example.restservice;

import java.io.Serializable;
import java.lang.reflect.*;
import java.math.BigDecimal;

public class Sample2 {

//    @Entity(name="student")
//    public class Student {
//
//        // fields, getters and setters
//
//    }

    static class AB implements Serializable{

    }

    static class ABC implements Cloneable{

    }

    static final class ABCD {
        final void methodA(){

        }
    }

    static class Singleton{
        private Singleton singleton;
        private Singleton(){

        }
        public Singleton getInstance(){
            if(singleton == null) {
                synchronized (this) {
                    if(singleton ==null) {
                        this.singleton = new Singleton();
                        return this.singleton;
                    }
                }
            }
            return this.singleton;
        }
    }

     abstract class  ABD {
        abstract void methodA();
        void methodB(){
            System.out.println();
        }

     }

     interface  ACD {
        void methodA();
        void methodB();
     }


    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        BigDecimal num1 = new BigDecimal("0.1"); // Exact representation
        BigDecimal num2 = new BigDecimal(0.1); // May introduce precision errors

        BigDecimal num = new BigDecimal("0.00");
        boolean isZero = num.compareTo(BigDecimal.ZERO) == 0; // true


        BigDecimal num3 = new BigDecimal("10");
        BigDecimal num4 = new BigDecimal("3");

        BigDecimal remainder = num3.remainder(num4); // 1

        Class<?> aClass = Class.forName("Sample.class");
        String canonicalName = aClass.getCanonicalName();
        Constructor<?>[] constructors = aClass.getConstructors();
        Method[] methods = aClass.getMethods();
        ClassLoader classLoader = aClass.getClassLoader();
        Class<?>[] classes = aClass.getClasses();
        Class<?>[] declaredClasses = aClass.getDeclaredClasses();
        AnnotatedType[] annotatedInterfaces = aClass.getAnnotatedInterfaces();
        AnnotatedType annotatedSuperclass = aClass.getAnnotatedSuperclass();
        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        Object[] enumConstants = aClass.getEnumConstants();
        Type[] genericInterfaces = aClass.getGenericInterfaces();
        Type genericSuperclass = aClass.getGenericSuperclass();
        int modifiers = aClass.getModifiers();
        Field[] declaredFields = aClass.getDeclaredFields();


        Object invoke = methods[0].invoke("", "", "", "");
        AB ab = new AB();
        ABC abc = new ABC();



    }
}
