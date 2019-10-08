import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class SignUpTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8088;
        RestAssured.basePath = "/api/users";
    }

    @ParameterizedTest
    @MethodSource("SignUpData#positive")
    public void positive(String username, String password) {
        Map<String, Object> body = new HashMap<String, Object>() {{ put("username", username); put("password", password); }};
        given().contentType(JSON).body(body)
                .when().post()
                .then().statusCode(201).and().body("username", equalTo(username)).log().all();
        given().contentType(JSON).and().body(body)
                .when().post("/auth")
                .then().statusCode(200).and().body("username", equalTo(username)).log().all();
    }

    @ParameterizedTest
    @MethodSource("SignUpData#incorrectLogin")
    public void signUpWithIncorrectLogin(String username, String password) {
        Map<String, Object> body = new HashMap<>();
        body.put("username", username);
        body.put("password", "Qwerty1");
        given().contentType(JSON).body(body)
                .when().post()
                .then().contentType(JSON).and()
                    .body("errors.username", hasSize(1)).and()
                    .body("errors.username[0]", equalTo("Email is not valid")).and()
                    .statusCode(400);
        given().contentType(JSON).and().body(body)
                .when().post("/auth")
                .then().statusCode(401).log().all();
    }

    @Test
    public void existingLogin() {
        String username = System.currentTimeMillis() + "@test";
        Map<String, Object> body = new HashMap<>();
        body.put("username", username);
        body.put("password", "Qwerty1");
        given().contentType(JSON).body(body).when().post().then().statusCode(201);
        given().contentType(JSON).body(body)
                .when().post()
                .then().contentType(JSON).and()
                    .body("errors.username", hasSize(1)).and()
                    .body("errors.username[0]", equalTo("Username already taken.")).and()
                    .statusCode(400);
        given().contentType(JSON).and().body(body)
                .when().post("/auth")
                .then().statusCode(200).and().body("username", equalTo(username)).log().all();
    }

    @Test
    public void existingLoginInDifferentRegister() {
        String username = System.currentTimeMillis() + "@test";
        Map<String, Object> body = new HashMap<>();
        body.put("username", username);
        body.put("password", "Qwerty1");
        given().contentType(JSON).body(body)
                .when().post()
                .then().statusCode(201);
        username = username.toUpperCase();
        given().contentType(JSON).body(body)
                .when().post()
                .then().contentType("application/json").and()
                    .body("errors.username", hasSize(1)).and()
                    .body("errors.username[0]", equalTo("Username already taken.")).and()
                    .statusCode(400);
    }

    @Test
    public void blankPassword() {
        String username = System.currentTimeMillis() + "@test";
        Map<String, Object> body = new HashMap<>();
        body.put("username", username);
        body.put("password", "");
        given().contentType(JSON).body(body)
                .when().post()
                .then().contentType(JSON).and()
                    .body("errors.username", hasSize(2)).and()
                    .body("errors.password[0]", equalTo("Password is required")).and()
                    .body("errors.password[1]", equalTo("Password length must be at least 6 characters")).and()
                    .statusCode(400);
    }

    @Test
    public void shortPassword() {
        String username = System.currentTimeMillis() + "@test";
        Map<String, Object> body = new HashMap<>();
        body.put("username", username);
        body.put("password", "Qwert");
        given().contentType(JSON).body(body)
                .when().post()
                .then().contentType(JSON).and()
                    .body("errors.password", hasSize(1)).and()
                    .body("errors.password[0]", equalTo("Password length must be at least 6 characters")).and()
                    .statusCode(400);
        given().contentType(JSON).and().body(body)
                .when().post("/auth")
                .then().statusCode(401).log().all();
    }
}
