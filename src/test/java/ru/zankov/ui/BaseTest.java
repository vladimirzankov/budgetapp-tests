package ru.zankov.ui;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void before() {
        Configuration.baseUrl = "http://localhost:8088";
    }
}
