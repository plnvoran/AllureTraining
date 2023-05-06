package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class RepositoryHomePage {

    private SelenideElement issuesTab = $("#issues-tab");

    @Step("Открываем вкладку Issue")
    public RepositoryHomePage selectIssuesTab() {
        issuesTab.click();
        return this;
    }

    @Step("Проверить имя {number} Issue")
    public RepositoryHomePage checkIssueName(String number, String name) {
        $(String.format("#issue_%s_link", number)).shouldHave(text(name));
        return this;
    }
}