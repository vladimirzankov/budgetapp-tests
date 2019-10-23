package ru.zankov.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import ru.zankov.model.Category;
import ru.zankov.model.CategoryReq;
import org.junit.jupiter.api.Test;
import ru.zankov.service.UserService;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static ru.zankov.EndpointUrl.CATEGORIES;
import static ru.zankov.utils.RandomUtils.randomEmail;

@TestInstance(PER_CLASS)
@DisplayName("API: Category creation")
public class CategoryCreationTest extends BaseTest {

    UserService user = new UserService();

    @Test
    public void test1() {
        String username = randomEmail(), password = randomAlphanumeric(10), currency = "$";
        user.signUp(username, password);
        String token = user.logIn(username, password).getToken();
        String name = randomAlphabetic(10), type = "INCOME";
        CategoryReq req = new CategoryReq(name, type);

        Category category = given().body(req).auth().oauth2(token).
                when().post(CATEGORIES).
                then().statusCode(201).extract().body().as(Category.class);

        assertEquals(category.getName(), name);
    }
}


// Инфо сисджет савеловская