
package com.test.classinterface;

public class InstanceInitTest {
    public static int k = 0;
    public static InstanceInitTest t1 = new InstanceInitTest("t1");
    public static int i = print("i");
    public static InstanceInitTest t2 = new InstanceInitTest("t2");
    public static int n = 99;

    static {
        print("静态块");
    };

    // the order of init obeys the order of definition
//    public int j = print("j");
//    public static int n = 99;

    {
        print("非静态构造块");
    }

    public int j = print("j");

    public InstanceInitTest(String str) {
        print(str);
        ++n;
        ++i;
    }

    public static int print(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "   n=" + n);
        ++n;
        return ++i;
    }

    public static void main(String[] args) {
        InstanceInitTest t = new InstanceInitTest("init");
    }
}
