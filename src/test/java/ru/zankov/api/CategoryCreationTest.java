package ru.zankov.api;

import ru.zankov.model.Category;
import ru.zankov.model.CategoryReq;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import ru.zankov.service.UserService;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.zankov.EndPoints.CATEGORIES;
import static ru.zankov.utils.RandomUtils.convert;

public class CategoryCreationTest extends BaseTest {

    UserService user = new UserService();

    @Test
    public void test1(TestInfo testInfo) {
        String username = convert(testInfo) + "@example.com6", password = "Qwerty1", currency = "$";
        user.signUp(username, password);
        String token = user.logIn(username, password).getToken();
        String name = convert(testInfo), type = "INCOME";
        CategoryReq req = new CategoryReq(name, type);

        /*given().body(req).auth().oauth2(token).
        when().post(CATEGORIES).
        then().statusCode(201).and().body("name", equalTo(name));*/

        Category category = given().body(req).auth().oauth2(token).
                when().post(CATEGORIES).
                then().statusCode(201).extract().body().as(Category.class);

        assertEquals(category.getName(), name);
    }
}


// Инфо сисджет савеловская