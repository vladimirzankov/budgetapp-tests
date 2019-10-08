import org.junit.jupiter.api.Test;
import pages.SignUpPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class SignUpTest {

    /*@Test
    public void positive() {
        String username = System.currentTimeMillis() + "@test";
        open("/signup");
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.signUpAs(username, "Qwerty1");
        signUpPage.successMessage.shouldHave(text("Sign Up success. You can Login now."));
    }

    @Test
    public void tab() {
        String username = System.currentTimeMillis() + "@test";
        open("/signup");
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.loginInput.val(username);
        signUpPage.loginInput.pressTab();
        signUpPage.passwordInput.val("Qwerty1");
        signUpPage.passwordInput.pressEnter();
        signUpPage.successMessage.shouldHave(text("Sign Up success. You can Login now."));
    }

    @Test
    public void incorrectLogin() {
        open("/signup");
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.signUpAs(" ", "Qwerty1");
        signUpPage.incorrectLoginMessage.shouldBe(visible);
        signUpPage.incorrectLoginMessage.shouldHave(text("Email is not valid"));
    }

    @Test
    public void blankPassword() {
        String username = System.currentTimeMillis() + "@test";
        open("/signup");
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.signUpAs(username, null);
        signUpPage.incorrectPasswordMessage.shouldBe(visible);
        signUpPage.incorrectPasswordMessage.shouldHave(text("Password is required"));
    }

    @Test
    public void shortPassword() {
        String username = System.currentTimeMillis() + "@test";
        open("/signup");
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.signUpAs(username, "Qwert");
        signUpPage.incorrectPasswordMessage.shouldBe(visible);
        signUpPage.incorrectPasswordMessage.shouldHave(text("Password length must be at least 6 characters"));
    }*/
}
