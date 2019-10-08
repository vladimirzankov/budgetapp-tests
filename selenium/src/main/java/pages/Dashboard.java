package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class Dashboard {

    public SelenideElement userDropdown = $(".user-menu .dropdown-toggle");

    public Dashboard() {
        userDropdown.shouldBe(visible);
    }
}
