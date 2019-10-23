package ru.zankov.utils;

public class RandomUtils {

    public static String randomEmail() {
        final String symbols = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder builder = new StringBuilder();
        int count = 20;
        while (count-- != 0) {
            int character = (int)(Math.random()*symbols.length());
            builder.append(symbols.charAt(character));
        }
        builder.append("@example.com");
        return builder.toString();
    }
}
