package ru.zankov.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.zankov.pages.AddCategory;
import ru.zankov.pages.ManageCategories;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@DisplayName("UI: Category creation")
public class CategoryCreationTest extends LoginFixture {

    @Test
    public void test1() {
        open("/categories");
        ManageCategories manageCategories = new ManageCategories();
        manageCategories.initCategoryCreation();
        AddCategory addCategory = new AddCategory();
        addCategory.createCategory("name", "Income");
    }
}
