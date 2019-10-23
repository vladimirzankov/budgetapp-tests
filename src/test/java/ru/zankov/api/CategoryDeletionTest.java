package ru.zankov.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.zankov.service.CategoryService;
import ru.zankov.service.UserService;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static ru.zankov.EndpointUrl.CATEGORIES;
import static ru.zankov.utils.RandomUtils.randomEmail;

@TestInstance(PER_CLASS)
@DisplayName("API: Category deletion")
public class CategoryDeletionTest extends BaseTest {

    UserService user = new UserService();
    CategoryService category = new CategoryService();

    @Test
    public void deleteCategory() {
        String username = randomEmail(), password = randomAlphanumeric(10);
        user.signUp(username, password);
        String token = user.logIn(username, password).getToken();
        int count = given().auth().oauth2(token).when().get(CATEGORIES).path("size()");
        System.out.println(count);
        int id = category.create(randomEmail(), "INCOME", token).getId();
        given().auth().oauth2(token).when().delete(CATEGORIES + "/" + id).then().statusCode(204);
    }
}
