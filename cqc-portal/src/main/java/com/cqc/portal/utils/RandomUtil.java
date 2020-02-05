package com.cqc.portal.utils;

import java.util.Random;

public class RandomUtil {

    private static String base = "ABCDEFGHJKMNPQRSTUVWXYZ23456789";

    private static String baseNumber = "0123456789";

    public static String generateChar(int length) {
        StringBuilder sb = new StringBuilder("");
        Random rd = new Random();
        for (int i = 0; i < length; i++) {
            int nextInt = rd.nextInt(30);
            sb.append(base.charAt(nextInt));
        }
        return sb.toString();
    }

    public static String generateCode(int length) {
        StringBuilder sb = new StringBuilder("");
        Random rd = new Random();
        for (int i = 0; i < length; i++) {
            int nextInt = rd.nextInt(9);
            sb.append(baseNumber.charAt(nextInt));
        }
        return sb.toString();
    }
}
