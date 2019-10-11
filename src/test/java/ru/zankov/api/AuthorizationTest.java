package ru.zankov.api;

import ru.zankov.model.AuthReq;
import ru.zankov.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static ru.zankov.EndPoints.*;
import static ru.zankov.utils.RandomUtils.convert;

@TestInstance(PER_CLASS)
@DisplayName("Authorization")
public class AuthorizationTest extends BaseTest {

    @ParameterizedTest(name = "{displayName} {arguments}")
    @MethodSource("ru.zankov.api.data.AuthorizationData#positive")
    @DisplayName("Authorize with correct username and password")
    public void positive(String username, String password) {
        AuthReq req = new AuthReq(username, password);

        given().body(req)
        .when().post(USERS)
        .then().statusCode(201).and().body("username", equalTo(username));

        given().body(req)
        .when().post(AUTH)
        .then().statusCode(200).and().body("username", equalTo(username));
    }

    @Test
    @DisplayName("Authorize with correct password in different register")
    public void positiveDifferentRegister(TestInfo testInfo) {
        String username = convert(testInfo) + "@example.com", password = "Qwerty1";
        AuthReq req = new AuthReq(username, password);

        given().body(req)
        .when().post(USERS)
        .then().statusCode(201).and().body("username", equalTo(username));

        req.setUsername(username.toUpperCase());

        given().body(req)
        .when().post(AUTH)
        .then().statusCode(200).and().body("username", equalTo(username));

        User user1 = given().body(req)
                .when().post(AUTH)
                .then().statusCode(200).and().extract().body().as(User.class);
        assertEquals(user1.getUsername(), username);
    }

    @ParameterizedTest(name = "{displayName} {arguments}")
    @MethodSource("ru.zankov.api.data.AuthorizationData#incorrectLogin")
    @DisplayName("Sign up with incorrect login")
    public void signUpWithIncorrectLogin(String username) {
        AuthReq req = new AuthReq(username, "Qwerty1");

        given().body(req)
        .when().post(USERS)
        .then().statusCode(400).and()
            .body("errors.username", hasSize(1)).and()
            .body("errors.username[0]", equalTo("Email is not valid"));

        given().body(req)
        .when().post(AUTH)
        .then().statusCode(401);
    }

    @Test
    @DisplayName("Sign up with existing login")
    public void existingLogin(TestInfo testInfo) {
        String username = convert(testInfo) + "@example.com5", password = "Qwerty1";
        AuthReq req = new AuthReq(username, password);

        given().body(req).when().post(USERS).then().statusCode(201);

        given().body(req)
        .when().post(USERS)
        .then().statusCode(400).and()
            .body("errors.username.size()", is(1)).and() //hasSize(1)).and()
            .body("errors.username[0]", equalTo("Username already taken."));

        given().body(req)
        .when().post(AUTH)
        .then().statusCode(200).and().body("username", equalTo(username));
    }

    @Test
    @DisplayName("Sign up with existing login in different register")
    public void existingLoginInDifferentRegister(TestInfo testInfo) {
        String username = convert(testInfo) + "@example.com", password = "Qwerty1";
        AuthReq req = new AuthReq(username, password);

        given().body(req).when().post(USERS).then().statusCode(201);

        username = username.toUpperCase();

        given().body(req)
        .when().post(USERS)
        .then().statusCode(400).and()
            .body("errors.username", hasSize(1)).and()
            .body("errors.username[0]", equalTo("Username already taken."));
    }

    @Test
    @DisplayName("Sign up with blank password")
    public void signUpWithBlankPassword(TestInfo testInfo) {
        String username = convert(testInfo) + "@example2.com", password = "Qwerty1";
        AuthReq req = new AuthReq(username, password);

        given().body(req)
        .when().post(USERS)
        .then().statusCode(400).and()
            .body("errors.password.size()", is(2)).and()
            .body("errors.password[0]", equalTo("Password is required")).and()
            .body("errors.password[1]", equalTo("Password length must be at least 6 characters"));
    }

    @Test
    @DisplayName("Sign up with short password")
    public void signUpWithShortPassword(TestInfo testInfo) {
        String username = convert(testInfo) + "@example.com", password = "Qwerty1";
        AuthReq req = new AuthReq(username, password);

        given().body(req)
        .when().post(USERS)
        .then().statusCode(400).and()
            .body("errors.password", hasSize(1)).and()
            .body("errors.password[0]", equalTo("Password length must be at least 6 characters"));

        given().body(req).when().post(AUTH).then().statusCode(401);
    }

    @Test
    @DisplayName("Login with blank username and password")
    public void loginWithBlankPasswordAndUsername(TestInfo testInfo) {
        String username = convert(testInfo) + "@example.com", password = "Qwerty1";
        AuthReq req = new AuthReq(username, password);

        given().body(req).when().post(USERS).then().statusCode(201);

        req = new AuthReq("", "");

        given().body(req).when().post(AUTH).then().statusCode(401);
    }

    @Test
    @DisplayName("Login with blank username")
    public void loginWithBlankUsername(TestInfo testInfo) {
        String username = convert(testInfo) + "@example.com", password = "Qwerty1";
        AuthReq req = new AuthReq(username, password);

        given().body(req).when().post(USERS).then().statusCode(201);

        req.setUsername("");

        given().body(req).when().post(AUTH).then().statusCode(401);
    }

    @Test
    @DisplayName("Login with blank password")
    public void loginWithBlankPassword(TestInfo testInfo) {
        String username = convert(testInfo) + "@example.com", password = "Qwerty1";
        AuthReq req = new AuthReq(username, password);

        given().body(req).when().post(USERS).then().statusCode(201);

        req.setPassword("");

        given().body(req).when().post(AUTH).then().statusCode(401);
    }

    @Test
    @DisplayName("Login with mixed username and password")
    public void loginWithMixedLoginAndPassword(TestInfo testInfo) {
        String username = convert(testInfo) + "@example.com", password = "Qwerty1";
        AuthReq req = new AuthReq(username, password);

        given().body(req).when().post(USERS).then().statusCode(201);

        req = new AuthReq(password, username);

        given().body(req).when().post(AUTH).then().statusCode(401);
    }

    @Test
    @DisplayName("Login with incorrect username")
    public void loginWithIncorrectUsername(TestInfo testInfo) {
        String username = convert(testInfo) + "@example.com", password = "Qwerty1";
        AuthReq req = new AuthReq(username, password);

        given().body(req).when().post(USERS).then().statusCode(201);

        req.setUsername(username.substring(0, username.length() - 1));

        given().body(req).when().post(AUTH).then().statusCode(401);
    }

    @Test
    @DisplayName("Login with incorrect password")
    public void loginWithIncorrectPassword(TestInfo testInfo) {
        String username = convert(testInfo) + "@example.com", password = "Qwerty1";
        AuthReq req = new AuthReq(username, password);

        given().body(req).when().post(USERS).then().statusCode(201);

        req.setPassword(password.substring(0, password.length() - 1));

        given().body(req).when().post(AUTH).then().statusCode(401);
    }

    @Test
    @DisplayName("Login with correct password in different register")
    public void loginWithCorrectPasswordInDifferentRegister(TestInfo testInfo) {
        String username = convert(testInfo) + "@example.com", password = "Qwerty1";
        AuthReq req = new AuthReq(username, password);

        given().body(req).when().post(USERS).then().statusCode(201);

        req.setPassword(password.toUpperCase());

        given().body(req).when().post(AUTH).then().statusCode(401);
    }
}
