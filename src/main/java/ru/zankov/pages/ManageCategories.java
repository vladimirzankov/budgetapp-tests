package ru.zankov.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ManageCategories {

    public SelenideElement searchInput = $("input"),
            addCategoryButton = $(".pull-right span"),
            submitDeletionButton = $(".modal-dialog [type=submit]");
    public List<SelenideElement> categories = $$("tbody tr");

    public ManageCategories() {
        // TODO
    }

    public void initCategoryCreation() {
        addCategoryButton.click();
    }

    public void deleteCategory(String name) {
        categories.stream()
                .filter(tr -> tr.$("td:first-of-type").text().equals(name))
                .forEach(tr -> tr.$("button").click());
        submitDeletionButton.click();
    }
}
