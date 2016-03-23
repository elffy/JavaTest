
package com.test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hex2String {

    /**
     * @param args
     * @throws UnsupportedEncodingException 
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        // TODO Auto-generated method stub
        String hex;
        //String hex = "59D3540DFF1A006100620063002A002B000D000A75358BDD53F77801FF1A00310032003300230023003400350036002A0037000D000A62405C5E7FA47EC4FF1A65E0000D000A62405C5E53F77C3FFF1A00550049004D53F77C3F0032";
//        hex = "6875616E6779696E6779696E67776F726B62616964752E636F6D";
        //hex = "3132332b2b3435362a37";
        hex = "CEDE";
        byte[] datas = hexStringToBytes(hex);
        System.out.println("String:" + new String(datas, "gb2312"));
        
        
        String test = "hello world";
        byte[] bytes = {
                0, 1, 2
        };
        System.out.println(bytes.length);
        System.out.println(test);
        test = handleString(test);
        System.out.println(test);

        String teststr = "0123456789_abcdef@";
        byte[] bs = teststr.getBytes();
        System.out.println("teststr:" + teststr + ", bytes length:" + teststr.getBytes().length);
        for (byte b:bs) {
            System.out.print(b);
        }
        String bytestr = bytesToHexString(bs);
        System.out.println("\n" + bytestr);
        bs = hexStringToBytes(bytestr);
        for (byte b:bs) {
            System.out.print(b);
        }
        try {
            String ret = new String(bs,"UTF-8");
            System.out.println("\n" + ret);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        String str = "史蒂夫郭德纲";   
        System.out.println(str.charAt(1));
        // the default for java is "UTF-8"
        bs = str.getBytes();
        bytestr = bytesToHexString(bs);
        System.out.println("史蒂夫郭德纲 length=" + bytestr.length() + " " +bytestr);
        System.out.println(new String(bs));

        try {
            bs = str.getBytes("utf-16");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        bytestr = bytesToHexString(bs);
        System.out.println("UTF-16:length=" + bytestr.length() + " " + bytestr);       
        System.out.println(new String(bs, "UTF-16"));
        
        try {
            bs = str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        bytestr = bytesToHexString(bs);
        System.out.println("UTF-16:length=" + bytestr.length() + " " + bytestr);       
        System.out.println(new String(bs, "ISO-8859-1"));
        
        ArrayList<String> al = new ArrayList<String>();
        ArrayList<String> al2 = new ArrayList<String>();
        al.add("hello");
        al2.add("world");
        al.addAll(al2);
        System.out.println(al);
        byte[] bytes2 = {(byte) 0xff,(byte) 0xff};
        int aaa = (byte)0xff;
        System.out.println(aaa);
        
        String[] strs1 = null;
        String[] strs2 = null;
        System.out.println((strs1 == strs2) + " " + (null == null));    //true;
        
        try {
            System.out.println(strs1[0]); //null String, java.lang.NullPointerException
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    /**
     * @param test
     * @return
     */
    private static String handleString(String test) {
        // TODO Auto-generated method stub
        return test + " haha!";
    }
    
    /**
     * Converts a byte array into a String of hexadecimal characters.
     *
     * @param bytes an array of bytes
     *
     * @return hex string representation of bytes array
     */
    public static String
    bytesToHexString(byte[] bytes) {
        if (bytes == null) return null;

        StringBuilder ret = new StringBuilder(2*bytes.length);

        for (int i = 0 ; i < bytes.length ; i++) {
            int b;

            b = 0x0f & (bytes[i] >> 4);

            ret.append("0123456789abcdef".charAt(b));

            b = 0x0f & bytes[i];

            ret.append("0123456789abcdef".charAt(b));
        }

        return ret.toString();
    }
    
    /**
     * Converts a hex String to a byte array.
     *
     * @param s A string of hexadecimal characters, must be an even number of
     *          chars long
     *
     * @return byte array representation
     *
     * @throws RuntimeException on invalid format
     */
    public static byte[]
    hexStringToBytes(String s) {
        byte[] ret;

        if (s == null) return null;

        int sz = s.length();

        ret = new byte[sz/2];

        for (int i=0 ; i <sz ; i+=2) {
            ret[i/2] = (byte) ((hexCharToInt(s.charAt(i)) << 4)
                                | hexCharToInt(s.charAt(i+1)));
        }

        return ret;
    }

    private static int hexCharToInt(char c) {
        if ('0' <= c && c <= '9') { return c - '0';}
        if ('A' <= c && c <= 'F') { return c - 'A' + 10;}
        if ('a' <= c && c <= 'f') { return c - 'a' + 10;}
        throw new RuntimeException ("invalid hex char '" + c + "'");
    }

}
