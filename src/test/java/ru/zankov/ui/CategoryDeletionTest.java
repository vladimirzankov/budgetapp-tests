package ru.zankov.ui;

import org.junit.jupiter.api.Test;
import ru.zankov.pages.ManageCategories;
import ru.zankov.service.CategoryService;

import java.util.UUID;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class CategoryDeletionTest extends LoginFixture {

    CategoryService category = new CategoryService();

    @Test
    public void delete() {
        String token = currentUser.getToken();
        String categoryName = UUID.randomUUID().toString();
        int id = category.create(categoryName, "INCOME", token).getId();
        open("/categories");
        ManageCategories manageCategories = new ManageCategories();
        assumeTrue(category.isCategoryExists(categoryName, token));
        manageCategories.deleteCategory(categoryName);
        assertFalse(category.isCategoryExists(categoryName, token));
    }
}
