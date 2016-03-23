package com.test;

public class AlgorithmTest {

    public static void main(String[] args) {
        
        String test = "哈无题滕";
        for (int i = 0; i < test.length(); i++) {
            char ch = test.charAt(i);
            int gbValue = getGBValue(ch);
            int index = getStartIndex(gbValue);
            System.out.println("ch:" +ch +",gbValue:" + Integer.toHexString(gbValue) + ",letter:" + sFirstLetters[index]);
        }
    }

    // the encoding value of sHanziTable in GB2312, 0xd8a1 is the first one in section 56.
    static final int[] sHanziValue = {
        0xb0a1, 0xb0c5, 0xb2c1, 0xb4ee, 0xb6ea, 0xb7a2,
        0xb8c1, 0xb9fe, 0xbbf7, 0xbfa6, 0xc0ac, 0xc2e8,
        0xc4c3, 0xc5b6, 0xc5be, 0xc6da, 0xc8bb, 0xc8f6,
        0xcbfa, 0xcdda, 0xcef4, 0xd1b9, 0xd4d1, 0xd8a1};
    private static final int FIRST = 0xb0a1;
    private static final int LAST = 0xd8a1;

    static final char[] sFirstLetters = {
        'A', 'B', 'C', 'D', 'E', 'F',
        'G', 'H', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S',
        'T', 'W', 'X', 'Y', 'Z', '#'};

    private static int getStartIndex(int gbValue) {
        int begin = 0;
        int end = sHanziValue.length - 1;
        int index = 0;
        int value;
        while (begin <= end) {
            index = (begin + end) >> 1;
            value = sHanziValue[index];
            System.out.println(begin + "-" + end + " index:" + index + ",value:" + Integer.toHexString(value));
            if (gbValue > value) {
                begin = index + 1;
            } else if (gbValue == value){
                break;
            } else {
                end = --index;
            }
        }
        return index;
    }

    /**
     * get encoding value of a char
     */
    private static int getGBValue(char ch) {
        String str = String.valueOf(ch);
        try {
            byte[] bytes = str.getBytes("GB2312");
            if (bytes.length < 2) {
                return 0;
            }
            return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
        } catch (Exception e) {
            return 0;
        }
    }

}
