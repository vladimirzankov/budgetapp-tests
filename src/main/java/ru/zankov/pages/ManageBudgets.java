package ru.zankov.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ManageBudgets {

    public SelenideElement searchInput = $("input"),
            addBudgetButton = $(".pull-right span");

    public ManageBudgets() {
        // TODO
    }
}
