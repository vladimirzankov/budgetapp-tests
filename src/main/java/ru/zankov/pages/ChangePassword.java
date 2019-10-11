package ru.zankov.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class ChangePassword {

    SelenideElement original = $("#original"),
            password = $("#password"),
            confirm = $("#confirm"),
            changePasswordButton = $("//button[normalize-space(text())='Change Password']");

    public ChangePassword() {
        original.shouldBe(visible);
    }
}
