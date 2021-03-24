package Methud.privat;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class DossierSteps {
    ElementsCollection DossierFilterCollection = $$(".custom-input");
    SelenideElement dossierList = $(".profiles-list"),
            lastNameDossier = $("#last-name"),
            firstNameDossier = $("#first-name"),
            middleNameInput = $("#middle-name"),
            buttonSaveResult = $(".app-button"),
            birthdayInput = $("#birthday"),
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
        return this;
    }

    @Step("Сортировка Персон по имени ->  {0}")
    public DossierSteps filterFirstName(String firstName) {
        firstNameDossier.val(firstName);
        return this;
    }

    @Step("Сортировка Персон по отчету -> {0}")
    public DossierSteps filterMiddleName(String middleName) {
        middleNameInput.val(middleName);
        return this;
    }

    @Step("Сортировка персон по году родения -> {0}")
    public DossierSteps filterHappyDay(int day) {
        birthdayInput.val(String.valueOf(day));
        return this;

    }

    @Step("Ввод номера паспорта :-> {0}")
    public DossierSteps inputNumberPassport(String passport) {
        $("#passport-range").val(passport);
        return this;
    }

    @Step("Ввод серии  паспорта :-> {0}")
    public DossierSteps inputSeriesPassport(String seriesPassport) {
        $("#passport-number").val(seriesPassport);
        return this;
    }

    @Step("Ввод отделения :-> {0}  выдачи паспорта")
    public DossierSteps passportissuingauthority(String key) {
        $("#passport-issuer").val(key);
        return this;
    }

    @Step("Ввод -> {0}  номер телефона для поиска персоны")
    public DossierSteps inputPhoneNumberDossier(String number) {
        DossierFilterCollection.get(0).val(number);
        return this;
    }

    @Step("Ввод значения почты -> {0} для поиска персоны ")
    public DossierSteps inputEmailDossier(String email) {
        DossierFilterCollection.get(1).val(email);
        return this;
    }

    @Step("Ввод значения в поле 'связанные социальные сети' : -> {0} для поиска персоны ")
    public DossierSteps inputDossierBinSnt(String binSnt) {
        DossierFilterCollection.get(2).val(binSnt);
        return this;
    }

    @Step("Открытие еще фильтров по персоне")
    public DossierSteps loadingMoreFilter() {
        $(".button-filters-toggle").click();
        return this;
    }

    @Step("Применение результатов поиска")
    public DossierSteps applicationResults() {
        buttonSaveResult.click();
        return this;
    }


    @Step("Проверка результатов вывода информации по дню рождения :-> {0}")
    public DossierSteps checkBrithday(String brithday) {
        SelenideElement fildResult = $$x("//span[contains(text(),'" + brithday + "')]").get(0).shouldBe(visible).shouldHave(text(brithday));
        return this;
    }


    @Step("Проверка результатов поиска по значению :->  {0}")
    public DossierSteps checkResultFilter(String check) {
        bounceWait.shouldBe(hidden);
        $$(".table-row-title").filter(text(check)).get(0).shouldHave(text(check));
        return this;

    }


}
