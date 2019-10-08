import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.proxy.SelenideProxyServer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.Dashboard;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class LogoutTest extends LoginFixture {

    /*@Test*/
    public void logout() throws Exception {
        Configuration.proxyEnabled = true;
        open("http://localhost.charlesproxy.com:8080/asdfghjkl");
        /*BrowserMobProxy selenideProxy = WebDriverRunner.getSelenideProxy().getProxy();
        selenideProxy.addHeader("Authorization", "Bearer 37c51c00-d004-488d-9a28-2cf84e8038d2");*/
        /*SelenideProxyServer proxyServer = WebDriverRunner.getSelenideProxy();
        proxyServer.addRequestFilter((request, contents, messageInfo)->{
            request.headers().add("my-test-header", "my-test-value");
            System.out.println(request.headers().entries().toString());
            return null;
        });*/

        BrowserMobProxy selenideProxy = WebDriverRunner.getSelenideProxy().getProxy();
        selenideProxy.addRequestFilter((request, contents, messageInfo)->{
            request.headers().add("Authorization", "Bearer 37c51c00-d004-488d-9a28-2cf84e8038d2");
            System.out.println(request.headers().entries().toString());
            return null;
        });
        RestAssured.baseURI = "http://localhost:8080";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("username", "a@a");
        jsonAsMap.put("password", "Qwerty1");
        String token = given().
                contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/api/users/auth")
                .then().statusCode(200).log().all()
                .extract()
                .path("token");
        //Cookie cookie1 = new Cookie("Idea-9da5366f", "7e0e6b48-f304-4edd-a851-70a07eda9842");
        Cookie cookie2 = new Cookie("auth", "%22YUBhOlF3ZXJ0eTE%3D%22");
        //WebDriverRunner.getWebDriver().manage().addCookie(cookie1);
        WebDriverRunner.getWebDriver().manage().addCookie(cookie2);
        open("http://localhost.charlesproxy.com:8080/dashboard");
        Dashboard dashboard = new Dashboard();
        Thread.sleep(30000);
    }

    /*@Test
    public void test2() {
        RestAssured.baseURI = "http://localhost:8080";
        Map<String, Object> body = new HashMap<>();
        body.put("username", "d@d");
        body.put("password", "Qwerty1");
        given().
                contentType("application/json")
                .body(body)
                .when()
                .post("/api/users")
                .then().statusCode(201).log().all();
    }*/
}


// localhost, 127.0.0.0/8, ::1