package ru.zankov.ui;

import org.junit.jupiter.api.BeforeEach;
import ru.zankov.model.User;
import ru.zankov.pages.LoginPage;
import ru.zankov.service.UserService;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static ru.zankov.utils.RandomUtils.*;

import static com.codeborne.selenide.Selenide.open;

public class LoginFixture extends BaseTest {

    User currentUser;
    UserService user = new UserService();

    @BeforeEach
    public void logIn() {
        String username = randomEmail(), password = randomAlphanumeric(10);
        user.signUp(username, password);
        open("/");
        currentUser = user.logIn(username, password);
        LoginPage loginPage = new LoginPage();
        loginPage.loginAs(username, password);

    }
}
