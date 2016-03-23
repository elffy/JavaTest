package com.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringTest {

    public static void main(String[] args) {
        String test = "3";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(sdf.format(date));
        test = String.format("dd%d", 4);
        System.out.println(test);
        String multiLine = "菊҉花҉输҉入҉法҉";
        System.out.println(multiLine.length() + "");
        String temp = "faldjflasjdfl   \\n\n";
        System.out.println(temp + ",end");
        System.out.println(temp.trim() + ",end");
        System.out.println(Integer.toHexString((int)'*'));
        System.out.println(Integer.toHexString((int)'★'));
        System.out.println(Integer.toHexString((int)'☆'));
        System.out.println("A:" + Character.digit('B', 16));
        String str = "a\\n b\nc";
        System.out.println(Hex2String.bytesToHexString(str.getBytes()));
        System.out.println(str);
        System.out.println(str.replace("\\n", "!!"));
        String a = "hello2"; 
        final String b = "hello";// final test
        String d = "hello";
        String c = b + 2;// initialized when compile
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));

        int aInt = 255;
        String intString = String.valueOf(aInt);
        System.out.println("intString:" + intString);
        System.out.println("int:" + Integer.parseInt(intString));
        
        
        int i,j;
        for (i=0;i<=3;i++){
            for (j=0;j<i;j++){
                System.out.print(" ");
            }
            System.out.print(i);
            for (j=0;j<3-i;j++){
                System.out.print(" ");
            }
            System.out.println();
         }
    }

}
