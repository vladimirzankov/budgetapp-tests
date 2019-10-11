package ru.zankov.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class LoginPage {

    public SelenideElement loginInput = $("#username"),
            passwordInput = $("#password"),
            submitButton = $("[type=submit]"),
            errorMessage = $(".alert-danger");

    public LoginPage() {
        loginInput.shouldBe(visible);
    }

    public void loginAs(String username, String password) {
        loginInput.val(username);
        passwordInput.val(password);
        submitButton.click();
    }
}
