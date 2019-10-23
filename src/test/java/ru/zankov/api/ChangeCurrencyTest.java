package ru.zankov.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import ru.zankov.model.AuthReq;
import ru.zankov.model.User;
import org.junit.jupiter.api.Test;
import ru.zankov.service.UserService;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static ru.zankov.EndpointUrl.AUTH;
import static ru.zankov.EndpointUrl.USERS;
import static ru.zankov.utils.RandomUtils.randomEmail;

@TestInstance(PER_CLASS)
@DisplayName("API: Change currency")
public class ChangeCurrencyTest extends BaseTest {

    UserService user = new UserService();

    @Test
    public void changeCurrency() {
        String username = randomEmail(), password = randomAlphanumeric(10), name = randomAlphabetic(10), currency = "$";
        user.signUp(username, password);
        String token = user.logIn(username, password).getToken();
        AuthReq req = new AuthReq(username, password);
        User user1 = given().body(req).when().post(AUTH).then().statusCode(200).and().extract().body().as(User.class);
        user1.setCurrency(currency);

        User user2 = given().auth().oauth2(token).body(user1)
                .when().put(USERS)
                .then().statusCode(200).extract().body().as(User.class);

        assertEquals(currency, user2.getCurrency());

    }
}
