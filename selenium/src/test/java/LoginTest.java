import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    /*@Test
    public void login() {

        open("/");
        LoginPage loginPage = new LoginPage();
        loginPage.loginAs("learner1@mailinator.com", "Qwerty1");

    }*/

    /*@Test
    public void login2() {

        open("/");
        LoginPage loginPage = new LoginPage();
        loginPage.loginAs("learner1@mailinator.com", "Qwerty");
        loginPage.errorMessage.shouldHave(text("Invalid username and password combination"));
    }*/
}
