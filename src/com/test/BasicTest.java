package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

public class BasicTest {

    public static int[] arrays = {1, 2, 3, 4};
    /**
     * @param args
     */
    public static void main(String[] args) {
//        floatVsDouble();
//        try {
//            WeakHashMapTest();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        
        ArrayListTest();
        System.out.println("arrays:" + arrays);
        int in = 0x80000000;
        System.out.println("i:" + in);
        System.out.println(Integer.MAX_VALUE);//打印最大整数:2147483647 
        System.out.println(Integer.MIN_VALUE);//打印最小整数:-2147483648 
        System.out.println(Float.MAX_VALUE);
        System.out.println(Float.MIN_VALUE);
        System.out.println(Double.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);
        System.out.println(Long.MAX_VALUE);
        
        //test for Integer.highestOneBit()
        int inta = 6;
        System.out.println(Integer.highestOneBit(inta) + "," + Integer.lowestOneBit(inta));
        int[] intArray = {1,2,3};
        String str = "hehe";
        clearArray(intArray, str);
        System.out.println("intArray=" + intArray[0] + "  str=" + str);
        System.out.println("s=" + Integer.highestOneBit(1080/90));
        boolean b = true;
        b = b || boolTest();//boolTest will not be called.
        System.out.println("2/3:" + 2/3);
        System.out.println("2f/3:" + 2f/3);
        InnerClass inner = new InnerClass(1);
        initInner(inner);
        System.out.println("inner:" + inner.mId);

    }

    private static void initInner(InnerClass inner) {
        inner.mId = 2;
    }

    static boolean boolTest() {
        System.out.println("boolTest");
        return false;
    }
    private static void clearArray(int[] intArray, String str) {
        intArray = null;
        str = null;
    }

    private static void ArrayListTest() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(Integer.valueOf(1));
        list.add(Integer.valueOf(2));
        list.add(Integer.valueOf(3));
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println("list:" + list);
        for (int i = 0; i < 10; i ++) {
            if (list.contains(i)) {
                System.out.println("list contains " + i);
            }
        }
//        for (int i : list) {
//            list.remove(Integer.valueOf(i));//java.util.ConcurrentModificationException
//        }

    }
    private static void floatVsDouble() {
        float f = (float) 100.1234;
        double d = 100.1234;
        long time1 = System.currentTimeMillis();
        System.out.println("begin:" + time1);
        for (int i = 0; i < 100000000; i++) {
            float f1 = f/1024;
            float f2 = f/(1024*1024);
            f += 100;
        }
        System.out.println("f/1024:" + f/1024);
        long time2 = System.currentTimeMillis();
        System.out.println("float takes time:" + (time2-time1));
        time1 = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            double d1 = d/1024;
            double d2 = d/(1024*1024);
            d += 100;
        }
        System.out.println("d/1024:" + (int)d/1024);
        time2 = System.currentTimeMillis();
        System.out.println("double takes time:" + (time2-time1));
    }

    private static void WeakHashMapTest() throws Exception {
        String a = new String("a");
        String b = new String("b");
        Map<String, String> weakmap = new WeakHashMap<String, String>();
        Map<String, String> map = new HashMap<String, String>();
        map.put(a, "aaa");
        map.put(b, "bbb");

        weakmap.put(a, "aaa");
        weakmap.put(b, "bbb");
        
        map.remove(a);
        
        a=null;
        b=null;
        
        System.gc();
        Iterator i = map.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry en = (Map.Entry)i.next();
            System.out.println("map:"+en.getKey()+":"+en.getValue());
        }

        Iterator j = weakmap.entrySet().iterator();
        while (j.hasNext()) {
            Map.Entry en = (Map.Entry)j.next();
            System.out.println("weakmap:"+en.getKey()+":"+en.getValue());
            
        }
    }

    static class InnerClass {
        int mId;
        public InnerClass(int id) {
            mId = id;
        }
    }
}
