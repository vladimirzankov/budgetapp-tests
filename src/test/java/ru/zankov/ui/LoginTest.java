package ru.zankov.ui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import ru.zankov.pages.Dashboard;
import ru.zankov.pages.LoginPage;
import ru.zankov.service.UserService;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static ru.zankov.utils.RandomUtils.randomEmail;

public class LoginTest extends BaseTest {

    UserService user = new UserService();

    @Test
    public void successful(TestInfo testInfo) {

        String username = randomEmail(), password = randomAlphanumeric(10);
        user.signUp(username, password);
        open("/");
        LoginPage loginPage = new LoginPage();
        loginPage.loginAs(username, password);
        Dashboard dashboard = new Dashboard();
        dashboard.userDropdown.shouldHave(text(username));
    }

    @Test
    public void incorrect() {

        String username = randomEmail(), password = randomAlphanumeric(10);
        user.signUp(username, password);
        open("/");
        LoginPage loginPage = new LoginPage();
        loginPage.loginAs(username.substring(0, username.length() - 1), password);
        loginPage.errorMessage.shouldHave(text("Invalid username and password combination"));
    }
}
