package com.vbakh.redisdemo.utils;


/**
 * Created by volodymyr.bakhmatiuk on 6/11/18.
 */
public class NumberUtils {

    public static final int NUMBER_LENGTH = 7;
    public static final String MIN_UUID = "";
    public static final String MAX_UUID = "\\xff";

    public static String toLexIndex(int x, int y, String id) {
        if (id.length() != 36) {
            throw new IllegalArgumentException();
        }
        return toIndexPrefix(x, y) + ":" + id;
    }

    public static String toMinLexIndex(int x, int y) {
        return toIndexPrefix(x, y) + ":" + MIN_UUID;
    }

    public static String toMaxLexIndex(int x, int y) {
        return toIndexPrefix(x, y) + ":" + MAX_UUID;
    }

    public static String toIndexPrefix(int x, int y) {
        String xBinary = Integer.toBinaryString(x);
        String yBinary = Integer.toBinaryString(y);
        if (xBinary.length() > NUMBER_LENGTH || yBinary.length() > NUMBER_LENGTH) {
            throw new IllegalArgumentException("Too big number");
        }
        while (xBinary.length() != NUMBER_LENGTH) {
            xBinary = "0" + xBinary;
        }
        while (yBinary.length() != NUMBER_LENGTH) {
            yBinary = "0" + yBinary;
        }
        String result = "";
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            result += xBinary.toCharArray()[i];
            result += yBinary.toCharArray()[i];
        }
        return result;
    }
}
