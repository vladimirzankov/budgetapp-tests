package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SignUpPage {

    public SelenideElement loginInput = $("#username"),
            passwordInput = $("#password"),
            submitButton = $("[type=submit]"),
            successMessage = $(".alert-success"),
            incorrectLoginMessage = $("#username + .help-block"),
            incorrectPasswordMessage = $("#password + .help-block");

    public SignUpPage() {
        loginInput.shouldBe(visible);
    }

    public void signUpAs(String username, String password) {
        loginInput.val(username);
        passwordInput.val(password);
        submitButton.click();
    }
}
