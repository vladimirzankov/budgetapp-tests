import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8088;
        //RestAssured.basePath = "/api/users/auth";
    }

    @Test
    public void login(TestInfo info) {
        String username = info.getTestClass().get().getName() + "@" + info.getTestMethod().get().getName();
        String password = "Qwerty1";
        Map<String, Object> body = new HashMap<String, Object>() {{
            put("username", username);
            put("password", password);
        }};
        body.put("username", username);
        body.put("password", password);
        given().contentType("application/json").body(body)
                .when().post("/api/users")
                .then().statusCode(201).and().body("username", equalTo(username)).log().all();
        given().contentType("application/json").and().body(body)
                .when().post("/api/users/auth")
                .then().statusCode(200).and().body("username", equalTo(username)).log().all();
    }
}
