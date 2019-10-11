package ru.zankov.utils;

import org.junit.jupiter.api.TestInfo;

public class RandomUtils {

    public static String convert(TestInfo info) {
        return info.getTestClass().get().getName() + "_" + info.getTestMethod().get().getName();
    }
}
