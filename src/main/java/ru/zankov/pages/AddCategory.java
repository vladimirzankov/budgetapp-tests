package ru.zankov.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class AddCategory {

    SelenideElement nameInput = $("#name"),
            typeRadio = $("#type"),
            submitButton = $("[type=submit]");

    public AddCategory() {
        nameInput.shouldBe(visible);
    }

    public void createCategory(String name, String type) {
        nameInput.val(name);
        typeRadio.selectRadio(type.toUpperCase());
        submitButton.click();
    }
}
