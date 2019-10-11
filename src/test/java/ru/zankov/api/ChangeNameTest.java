package ru.zankov.api;

import ru.zankov.model.AuthReq;
import ru.zankov.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import ru.zankov.service.UserService;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.zankov.EndPoints.AUTH;
import static ru.zankov.EndPoints.USERS;
import static ru.zankov.utils.RandomUtils.convert;

public class ChangeNameTest extends BaseTest {

    UserService user = new UserService();

    @Test
    public void changeName(TestInfo testInfo) {
        String username = convert(testInfo) + "@example.com5", password = "Qwerty1", name = "test";
        user.signUp(username, password);
        String token = user.logIn(username, password).getToken();
        AuthReq req = new AuthReq(username, password);
        User user1 = given().body(req).when().post(AUTH).then().statusCode(200).and().extract().body().as(User.class);
        user1.setName(name);

        User user2 = given().auth().oauth2(token).body(user1)
                .when().put(USERS)
                .then().statusCode(200).extract().body().as(User.class);

        assertEquals(name, user2.getName());

    }
}
