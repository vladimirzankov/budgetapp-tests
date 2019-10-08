import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LoginFixture {

    @BeforeEach
    public void login() {
        /*System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8888");
        RestAssured.baseURI = "https://otus-test-budget-app.herokuapp.com";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("username", "learner1@mailinator.com");
        jsonAsMap.put("password", "Qwerty1");
        given().
                contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/api/users/auth")
                .then().statusCode(200).log().all();*/
    }
}
