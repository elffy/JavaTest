package com.test;

public class ExtendsTest {

//    abstract class BasePeople {
//        Object car;
//        Object house;
//        abstract Object getCar();
//        abstract Object getHouse();
//    }
//
//    class RichGeneration1 extends BasePeople {
//
//        @Override
//        Object getCar() {
//            return buyACar();
//        }
//
//        @Override
//        Object getHouse() {
//            return buyAHouse();
//        }
//    }
//
//    class RichGeneration2 extends RichGeneration1 {
//
//        @Override
//        Object getCar() {
//            return super.getCar();
//        }
//
//        @Override
//        Object getHouse() {
//            return super.getHouse();
//        }
//    }
//
//    class PoorGeneration1 extends BasePeople {
//
//        @Override
//        Object getCar() {
//            throw UnsupportedOperationException("Illegal Request!");
//        }
//
//        @Override
//        Object getHouse() {
//            throw UnsupportedOperationException("Illegal Request!");
//        }
//    }
//
//    class PoorGeneration2 extends PoorGeneration1 {
//
//        @Override
//        Object getCar() {
//            return super.getCar();
//        }
//
//        @Override
//        Object getHouse() {
//            return super.getHouse();
//        }
//    }

    static class TimeBase {
        String timeZone;
        final int id;
        TimeBase(){
            timeZone = "beijing";
            id = 0;
            System.out.println("TimeBase:" + timeZone);
        }
        TimeBase(String tz, int id) {
            timeZone = tz;
            this.id = id;
            System.out.println("TimeBase:" + timeZone);
        }
    }

    static class TimeEx extends TimeBase{
        TimeEx(){
            timeZone = "shanghai";
            System.out.println(timeZone);
        }
        TimeEx(String tz, int id){
//            super(tz, id);
            System.out.println(timeZone);
        }
    }

    public static void main(String[] args) {
        TimeEx time = new TimeEx();
        time = new TimeEx("haha", 5);
    }
}
