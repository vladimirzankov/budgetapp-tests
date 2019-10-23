package ru.zankov.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.zankov.pages.ManageCategories;
import ru.zankov.service.CategoryService;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@DisplayName("UI: Category deletion")
public class CategoryDeletionTest extends LoginFixture {

    CategoryService category = new CategoryService();

    @Test
    public void delete() {
        String token = currentUser.getToken();
        String categoryName = randomAlphabetic(10);
        int id = category.create(categoryName, "INCOME", token).getId();
        open("/categories");
        ManageCategories manageCategories = new ManageCategories();
        assumeTrue(category.isCategoryExists(categoryName, token));
        manageCategories.deleteCategory(categoryName);
        assertFalse(category.isCategoryExists(categoryName, token));
    }
}
