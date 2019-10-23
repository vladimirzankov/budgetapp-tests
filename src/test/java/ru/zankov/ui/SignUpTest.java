package ru.zankov.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.zankov.pages.SignUpPage;
import ru.zankov.service.UserService;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static ru.zankov.utils.RandomUtils.randomEmail;

@TestInstance(PER_CLASS)
@DisplayName("UI: Sign up")
public class SignUpTest extends BaseTest {

    @Test
    public void successful() {
        String username = randomEmail(), password = randomAlphanumeric(10);
        open("/signup");
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.signUpAs(username, password);
        signUpPage.successMessage.shouldHave(text("Sign Up success. You can Login now."));
    }

    @Test
    public void tab() {
        String username = randomEmail(), password = randomAlphanumeric(10);
        open("/signup");
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.loginInput.val(username);
        signUpPage.loginInput.pressTab();
        signUpPage.passwordInput.val(password);
        signUpPage.passwordInput.pressEnter();
        signUpPage.successMessage.shouldHave(text("Sign Up success. You can Login now."));
        UserService user = new UserService();
    }

    @Test
    public void incorrectLogin() {
        String password = randomAlphanumeric(10);
        open("/signup");
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.signUpAs(" ", password);
        signUpPage.incorrectLoginMessage.shouldBe(visible);
        signUpPage.incorrectLoginMessage.shouldHave(text("Email is not valid"));
    }

    @Test
    public void blankPassword() {
        String username = randomEmail();
        open("/signup");
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.signUpAs(username, null);
        signUpPage.incorrectPasswordMessage.shouldBe(visible);
        signUpPage.incorrectPasswordMessage.shouldHave(text("Password is required"));
    }

    @Test
    public void shortPassword() {
        String username = randomEmail(), password = randomAlphanumeric(5);
        open("/signup");
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.signUpAs(username, password);
        signUpPage.incorrectPasswordMessage.shouldBe(visible);
        signUpPage.incorrectPasswordMessage.shouldHave(text("Password length must be at least 6 characters"));
    }
}

