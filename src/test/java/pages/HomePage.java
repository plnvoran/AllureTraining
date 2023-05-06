package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private SelenideElement searchingField = $(".header-search-input");

    @Step("Поиск репозитория {repository}")
    public HomePage searchRepository(String repository) {
        searchingField.setValue(repository);
        searchingField.submit();
        return this;
    }
}