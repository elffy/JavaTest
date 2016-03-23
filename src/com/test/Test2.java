package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test2 {

    public enum AppType{
        APPTYPE_UNKNOWN,
        APPTYPE_SIM,
        APPTYPE_USIM,
        APPTYPE_RUIM,
        APPTYPE_CSIM,
        APPTYPE_ISIM
    };
    
    static class AsyncResult{
        int id;
        Object obj;
        AsyncResult(int id, Object obj) {
            this.id = id;
            this.obj = obj;
        }
        @Override
        public String toString() {
            return "" + id + "," + obj;
        }
    }
    static AppType type3 = AppType.APPTYPE_SIM;
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AppType type1 = AppType.APPTYPE_SIM;
        System.out.println(type1);
        System.out.println(type1.ordinal() + ":" + type1.toString());
        AppType type2 = AppType.APPTYPE_USIM;
        
        switch(type3) {
            case APPTYPE_UNKNOWN:
                System.out.println("ddd");
                break;
            default:
                System.out.println("default");
                    break;
        }
        String types = "";
        types += type1 + ",";
        types += type2 + ",";
        System.out.println(types);       
        String[] appTypes = types.split(",");
        for (String string : appTypes) {
            System.out.println(string);
        }
        String str = type1 + "";
        System.out.println(types.contains(AppType.APPTYPE_USIM.toString()));
        
        test00();   //initialize arrayList
        
        test01();   //
        
        StringBuilder ret = new StringBuilder(4);
        System.out.println(" ret=" + ret.toString() + " ");
        
        AsyncResult test = new AsyncResult(1, null);
        String hahaha = (String) test.obj;
        //int haha = (Integer) test.obj;    //cant force transfer null to Integer!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        Integer in = null;
//        int iin = in;
//        System.out.println(iin);
        AsyncResult test2 = new AsyncResult(1, "haha");
        System.out.println(hahaha);
        if (hahaha != null && hahaha.contains(" ")) {
            System.out.println("zzzzzzz");
        }
        System.out.println("hahahahah");
        byte[] result = new byte[3];
        System.out.println("result.length=" + result.length);
        for (byte b:result) {
            b = -1;
        }
        System.out.println(result[1]);     //byte/int are basic type,not reference, so result won't change
        
        for (int i = 0; i < 3; i++) {
            result[i] = -1;                //should be initialized like this
        }
        System.out.println(result[1]);
        
        for (int i = 0; i < 3; i++) {
            switch(i) {
                case 3:
                    System.out.println("3333");
                default: 
                    break; //break the switch
            }
            System.out.println("default");
        }
        
        int i = 24;
        byte b = (byte) (((i/10)<<4) + i%10);
        System.out.println(b + "," + ((i/10)<<4) + i%10 + ",");
        b = (byte)((((i / 10) << 4) & 0xf0) | ((i % 10) & 0x0f));
        System.out.println(b);
        byte bb = (byte) 0xaf;
        int int1 = bb;
        int int2 = bb & 0xff;
        System.out.println(int1 + "," + int2);
       // byte[] netby = new byte[-1];
        String temp = sStr;
        sStr = null;
        System.out.println(temp);//the reference sStr is null, but the object still exists
    }
private static String sStr = "ddddddddd";

    public Map<Integer, String> test03() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "haha");
        return map;
    }
    private static void test01() {
        String[] addresses = new String[0];
        System.out.println("addresses=" + addresses);
        
        String sql = "index=5";
        int index = Integer.parseInt(sql.substring(sql.indexOf("=") + 1));
        System.out.println("index=" + index);
        
        String test = null;
        System.out.println("hello " + test == null ? "world" : "oops"); // == operator's priority is very low
        System.out.println("hello " + (test == null ? "world" : "oops"));
        
    }

    private static void test00() {
        Map<Integer,ArrayList<byte[]>> mIapFileRecord = new  HashMap<Integer,ArrayList<byte[]>>();
//      byte[] byte1 = new byte[1];
//      ArrayList<byte[]> array1 =  new ArrayList<byte[]>();
//      for (int i = 0; i < 10; i++) {
//          array1.add(byte1);
//      }  
//*******if initialized like above, then all the data point to the same reference zjl*******
      byte[] byte1 = null;
      ArrayList<byte[]> array1 =  new ArrayList<byte[]>();
      for (int i = 0; i < 10; i++) {
          byte1 = new byte[1];
          byte1[0] = (byte) 0xff;
          array1.add(byte1);
      }
      int i = 0xff;
      System.out.println("i=" + i);
      i = byte1[0];
      System.out.println("i=" + i);
      mIapFileRecord.put(1, array1);
      byte[] byte2 = mIapFileRecord.get(1).get(5);
      System.out.println(byte2);
      System.out.println(mIapFileRecord.get(1).get(6));
      byte2[0] = 10;
      mIapFileRecord.get(1).set(5, byte2);
      for (byte[] record : mIapFileRecord.get(1)) {
          System.out.println(record[0]);
      }

      ArrayList<byte[]> test = mIapFileRecord.get(-1);  //test = null
      System.out.println("test=" + test);
    }

}
