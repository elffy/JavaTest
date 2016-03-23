package com.test;

public class ThreadTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("mainThread:" + Thread.currentThread().toString());
        new Thread(new Runnable(){

            @Override
            public void run() {
                System.out.println("workThread:" + Thread.currentThread().toString());
                for (int i = 0; i < 10; i++) {                    
                    System.out.println("time:" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            
        }).start();
        System.exit(0);
    }
}
