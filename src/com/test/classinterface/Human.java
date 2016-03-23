package com.test.classinterface;

public class Human extends Animal{

    public static void main(String[] args) {
        doAction(new Human());//interface can be extended.

    }

    private static void doAction(Interface1 interface1) {
        interface1.eat();
        interface1.sleep();
    }

    @Override
    public void eat() {
        System.out.println("Human eating");

    }

    @Override
    public void sleep() {
        System.out.println("Human sleeping");
        
    }
}
