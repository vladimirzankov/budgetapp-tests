package ru.zankov.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class Profile {

    public SelenideElement nameInput = $("#name"),
            currencyInput = $("#currency"),
            updateButton = $("//button[normalize-space(text())='Update']");

    public Profile() {
        nameInput.shouldBe(visible);
    }
}
