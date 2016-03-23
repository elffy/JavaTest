package com.test.codec;

public class TestClass {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(GsmAlphabet.sLanguageTables[0]);
        System.out.println("position:" + GsmAlphabet.sLanguageTables[0].indexOf(" "));
        System.out.println("position:" + GsmAlphabet.sCharsToGsmTables[0].get(' ', ' '));
        int size = GsmAlphabet.sCharsToGsmTables[0].size();
        for (int i = 0; i < size; i++) {
            System.out.println(i + " key:" + (char)GsmAlphabet.sCharsToGsmTables[0].keyAt(i) + 
                    ", value:" + GsmAlphabet.sCharsToGsmTables[0].valueAt(i));
        }
    }

}
