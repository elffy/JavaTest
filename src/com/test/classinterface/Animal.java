package com.test.classinterface;

public class Animal implements Interface1 {

    @Override
    public void eat() {
        System.out.println("Animal eating");

    }

    public void run() {
        System.out.println("Animal runing");
    }

    @Override
    public void sleep() {
        System.out.println("Animal sleeping");
        
    }
}
