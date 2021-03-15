package Methud.privat;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.linkText;

public class DossierSteps {
    SelenideElement dossierList = $(".profiles-list"),
            lastNameDossier = $("#last-name"),
            firstNameDossier = $("#first-name"),
            buttonSaveResult = $(".app-button"),
            bounceWait = $(".double-bounce2");

    @Step("Открытие раздела {0}")
    public DossierSteps openDossier(String namePage) {
        $(linkText(namePage)).click();
        $(dossierList).shouldBe(visible);
        return this;
    }

    @Step("Сортировка Персон по фамилии ->  {0}")
    public DossierSteps filterLastName(String lastName) {
        lastNameDossier.val(lastName);
        buttonSaveResult.click();
        return this;
    }

    @Step("Сортировка Персон по имени ->  {0}")
    public DossierSteps filterFirstName(String firstName) {
        firstNameDossier.val(firstName);
        buttonSaveResult.click();
        return this;
    }


    @Step("Проверка результатов поиска по значению ->  {0}")
    public DossierSteps checkResultFilter(String check) {
        bounceWait.shouldBe(hidden);
        $$(".table-row-title").filter(text(check)).get(0).shouldHave(text(check));
        return this;

    }

}
