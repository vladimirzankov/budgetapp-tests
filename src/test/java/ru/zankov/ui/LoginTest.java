package ru.zankov.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import net.lightbody.bmp.BrowserMobProxy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.Cookie;
import ru.zankov.pages.Dashboard;
import ru.zankov.pages.LoginPage;
import ru.zankov.service.UserService;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static ru.zankov.utils.RandomUtils.convert;

public class LoginTest extends BaseTest {

    UserService user = new UserService();

    @Test
    public void successful(TestInfo testInfo) {

        String username = convert(testInfo) + "@example.com16", password = "Qwerty1";
        user.signUp(username, password);
        open("/");
        LoginPage loginPage = new LoginPage();
        loginPage.loginAs(username, password);
        //loginPage.errorMessage.shouldHave(text("Invalid username and password combination"));
        Dashboard dashboard = new Dashboard();
        dashboard.userDropdown.shouldHave(text(username));
    }

    @Test
    public void incorrect(TestInfo testInfo) {

        String username = convert(testInfo) + "@example.com18", password = "Qwerty1";
        user.signUp(username, password);
        open("/");
        LoginPage loginPage = new LoginPage();
        loginPage.loginAs(username.substring(0, username.length() - 1), password);
        loginPage.errorMessage.shouldHave(text("Invalid username and password combination"));
    }

    @Test
    public void loginCookies() {
        Configuration.baseUrl = "http://localhost:8088";
        Configuration.proxyEnabled = true;
        open("/");
        LoginPage loginPage = new LoginPage();
        //loginPage.loginAs("a@a", "Qwerty1");
        BrowserMobProxy selenideProxy = WebDriverRunner.getSelenideProxy().getProxy();
        selenideProxy.addRequestFilter((request, contents, messageInfo)->{
            request.headers().add("Authorization", "Bearer 75dbce49-b567-4471-87e7-9fb9d2eb524e");
            System.out.println(request.headers().entries().toString());
            return null;
        });
        Cookie cookie1 = new Cookie("JSESSIONID.f9422bcf", "node01og0vq4im5e8y1baqjzudnn7s26.node0");
        Cookie cookie2 = new Cookie("auth", "%22YUBhOlF3ZXJ0eTE%3D%22");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie1);
        WebDriverRunner.getWebDriver().manage().addCookie(cookie2);
        open("/dashboard");
    }
}

//


