package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.RepositoryHomePage;
import pages.SearchingResultsPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@Feature("Issue из репозитория")
@Story("Отображение имени Issue во вкладке Issue")
@Owner("plnvoran")
@Severity(SeverityLevel.NORMAL)
@Link(value = "prod", url = "https://github.com")
public class CheckIssueNameTests extends TestBase {

    private HomePage mainPage = new HomePage();
    private SearchingResultsPage resultSearchPage = new SearchingResultsPage();
    private RepositoryHomePage repositoryPage = new RepositoryHomePage();

    private static final String
            REPOSITORY = "plnvoran/test55",
            ISSUE_NUMBER = "1",
            ISSUE_NAME = "Issue for allure";

    @BeforeEach
    public void beforeEach() {
        open("");
    }

    @DisplayName("Проверка имени Issue с номером \"" + ISSUE_NUMBER + "\"" + " методом Чистый Selenide (с Listener)")
    @Test
    public void checkIssueNameWithClearListenerTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        $(".header-search-input").setValue(REPOSITORY);
        $(".header-search-input").submit();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $("#issue_" + ISSUE_NUMBER + "_link").shouldHave(text(ISSUE_NAME));
    }

    @DisplayName("Проверка имени Issue с номером \"" + ISSUE_NUMBER + "\"" + " методом Лямбда шаги через step")
    @Test
    public void checkIssueNameWithLambdaStepsTest() {

        step("Поиск репозитория " + REPOSITORY, () -> {
            $(".header-search-input").setValue(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Открыть страницу репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем вкладку Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером " + ISSUE_NUMBER + "и именем " + ISSUE_NAME, () -> {
            $("#issue_" + ISSUE_NUMBER + "_link").shouldHave(text(ISSUE_NAME));
        });
    }

    @DisplayName("Проверка имени Issue с номером \"" + ISSUE_NUMBER + "\"" + " методом Шаги с аннотацией @Step")
    @Test
    public void checkIssueNameWithAnnotatedStepsTest() {
        mainPage.searchRepository(REPOSITORY);
        resultSearchPage.selectRepository(REPOSITORY);
        repositoryPage.selectIssuesTab();
        repositoryPage.checkIssueName(ISSUE_NUMBER, ISSUE_NAME);
    }


}