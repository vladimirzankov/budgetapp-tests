package ru.zankov.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class AddBudget {

    SelenideElement categoryInput = $("#categoryId"),
            nameInput = $("#name"),
            projectedInput = $("#projected");

    public AddBudget() {
        categoryInput.shouldBe(visible);
    }
}
