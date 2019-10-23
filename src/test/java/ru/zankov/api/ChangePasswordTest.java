package ru.zankov.api;

import ru.zankov.model.AuthReq;
import ru.zankov.model.PasswordReq;
import org.junit.jupiter.api.*;
import ru.zankov.service.UserService;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static ru.zankov.EndpointUrl.*;
import static ru.zankov.utils.RandomUtils.randomEmail;

@TestInstance(PER_CLASS)
@DisplayName("API: Change password")
public class ChangePasswordTest extends BaseTest {

    UserService user = new UserService();

    @Test
    @DisplayName("Change password to a new correct password")
    public void correct() {
        String username = randomEmail(),
                original = randomAlphanumeric(10),
                password = original,
                confirm = original;
        user.signUp(username, password);
        String token = user.logIn(username, password).getToken();
        PasswordReq passwordReq = new PasswordReq(original, password, confirm);

        given().auth().oauth2(token).and().body(passwordReq)
        .when().put(PASSWORD)
        .then().statusCode(200);

        AuthReq authReq = new AuthReq(username, password);

        given().body(authReq).when().post(AUTH).then().statusCode(200);
    }

    @Test
    @DisplayName("Change password to the same password")
    public void correctNew() {
        String username = randomEmail(),
                original = randomAlphanumeric(10),
                password = randomAlphanumeric(10),
                confirm = password;
        user.signUp(username, original);
        String token = user.logIn(username, original).getToken();
        PasswordReq passwordReq = new PasswordReq(original, password, confirm);

        given().auth().oauth2(token).and().body(passwordReq)
        .when().put(PASSWORD)
        .then().statusCode(200);

        AuthReq authReq = new AuthReq(username, original);

        given().body(authReq).when().post(AUTH).then().statusCode(401);

        authReq.setPassword(password);

        given().body(authReq).when().post(AUTH).then().statusCode(200);
    }
}
