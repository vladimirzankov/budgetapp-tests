package ru.zankov.ui;

import org.junit.jupiter.api.BeforeEach;
import ru.zankov.model.User;
import ru.zankov.pages.LoginPage;
import ru.zankov.service.UserService;

import java.util.UUID;

import static com.codeborne.selenide.Selenide.open;

public class LoginFixture extends BaseTest {

    User currentUser;
    UserService user = new UserService();

    @BeforeEach
    public void logIn() {
        String username = UUID.randomUUID().toString() + "@example.com", password = "Qwerty1";
        user.signUp(username, password);
        open("/");
        currentUser = user.logIn(username, password);
        LoginPage loginPage = new LoginPage();
        loginPage.loginAs(username, password);

    }
}
