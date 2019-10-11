package ru.zankov.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class Dashboard {

    public SelenideElement userDropdown = $(".user-menu .dropdown-toggle"),
            profileButton = $(".pull-left a"),
            configurationButton = $("[title='Manage my configuration']"),
            categoriesButton = $("[title='Manage my categories']"),
            budgetsButton = $("[title='Manage my budgets']");

    public Dashboard() {
        userDropdown.shouldBe(visible);
    }

    public void openProfile() {
        userDropdown.click();
        profileButton.shouldBe(visible);
        profileButton.click();
    }

    public void goToManageCategories() {
        configurationButton.click();
        categoriesButton.shouldBe(visible);
        categoriesButton.click();
    }

    public void goToManageBudgets() {
        configurationButton.click();
        budgetsButton.shouldBe(visible);
        budgetsButton.click();
    }
}
