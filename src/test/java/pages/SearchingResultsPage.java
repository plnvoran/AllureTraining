package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.linkText;

public class SearchingResultsPage {

    @Step("Открыть страницу репозитория {repository}")
    public SearchingResultsPage selectRepository(String repository) {
        $(linkText(repository)).click();
        return this;
    }
}
