
package com.test.classinterface;

public class OverrideTest {

    static class Foo {
        int i = 0;
        void print() {
            System.out.println("Foo i:" + i);
        }
    }

    static class Bar extends Foo {
        Bar() {
            this(0);
        }
        Bar(int i) {
            
        }
        int i = 1;
        void print() {
            super.print();
            System.out.println("Bar i:" + i);
        }
    }

    public static void main(String... args) {
        Foo foo = new Bar();
        System.out.println(foo.i);
        foo.i = 2;
        System.out.println(foo.i);
        foo.print();
    }
}
