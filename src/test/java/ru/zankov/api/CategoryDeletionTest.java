package ru.zankov.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import ru.zankov.service.CategoryService;
import ru.zankov.service.UserService;

import static io.restassured.RestAssured.given;
import static ru.zankov.EndPoints.CATEGORIES;
import static ru.zankov.utils.RandomUtils.convert;

public class CategoryDeletionTest extends BaseTest {

    UserService user = new UserService();
    CategoryService category = new CategoryService();

    @Test
    public void deleteCategory(TestInfo testInfo) {
        String username = convert(testInfo) + "@example.com11", password = "Qwerty1", name = "test";
        user.signUp(username, password);
        String token = user.logIn(username, password).getToken();
        int count = given().auth().oauth2(token).when().get(CATEGORIES).path("size()");
        System.out.println(count);
        int id = category.create(convert(testInfo), "INCOME", token).getId();
        given().auth().oauth2(token).when().delete(CATEGORIES + "/" + id).then().statusCode(204);
    }
}
