package privat;

import Methud.privat.DossierSteps;
import com.codeborne.selenide.SelenideElement;
import config.BasePrivat;
import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.$;


public class DossierPage extends BasePrivat {
    String filterLastName = "Герасимов",
            filterName = "Александр",
            middleName = "Сергеевич";

    DossierSteps steps = new DossierSteps();

    SelenideElement dossierList = $(".profiles-list"),
            lastNameDossier = $("#last-name"),
            firstNameDossier = $("#first-name"),
            buttonSaveResult = $(".app-button"),
            bounceWait = $(".double-bounce2");


    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Открытие раздела персоны")
    void selectDossier() {
        steps.openDossier("Персоны");
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Сортировка персоны по фамилии")
    void DossierFilterLastName() {
        steps.openDossier("Персоны");
        steps.filterLastName(filterLastName);
        steps.applicationResults();
        steps.checkResultFilter(filterLastName);
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Сортировка персоны по Имени")
    void DossierFilterName() {
        steps.openDossier("Персоны");
        steps.filterFirstName(filterName);
        steps.applicationResults();
        steps.checkResultFilter(filterName);

    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Сортировка персоны по Имени и Фамилии")
    void DossierFilterNameAndLastName() {
        steps.openDossier("Персоны");
        steps.filterLastName(filterLastName);
        steps.filterFirstName(filterName);
        steps.applicationResults();
        steps.checkResultFilter(filterLastName + " " + filterName);

    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Сортировка персоны по Отчеству")
    void DossierFilterMiddleName() {
        steps.openDossier("Персоны");
        steps.filterMiddleName(middleName);
        steps.applicationResults();
        steps.checkResultFilter(middleName);

    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Сортировка персоны по Фамилии, Имени и Отчеству")
    void DossierFilterMiddleNameAndNameAndFirstName() {
        steps.openDossier("Персоны");
        steps.filterLastName(filterLastName);
        steps.filterMiddleName(middleName);
        steps.filterFirstName(filterName);
        steps.applicationResults();
        steps.checkResultFilter(filterLastName + " " + filterName + " " + middleName);

    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Сортировка персоны по дню рождения")
    void DossierFilterBrithday() {
        steps.openDossier("Персоны");
        steps.filterHappyDay(12_30_1994);
        steps.applicationResults();
        steps.checkBrithday("1994-12-30");

    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Disabled("Не работает")
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Сортировка персоны по номеру паспорта и серии")
    void DossierFilterPassport() {
        steps.openDossier("Персоны");
        steps.loadingMoreFilter();
        steps.inputNumberPassport("3840");
        steps.inputSeriesPassport("384215");
        steps.passportissuingauthority("Отделением УФМС");
        steps.applicationResults();
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Disabled("Не работает")
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Сортировка персоны по номеру телефона")
    void DossierFilterNumber() {
        steps.openDossier("Персоны");
        steps.loadingMoreFilter();
        steps.inputPhoneNumberDossier("890251029407");
        steps.applicationResults();

    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Disabled("Не работает")
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Сортировка персоны по email ")
    void DossierFilterEmail() {
        steps.openDossier("Персоны");
        steps.loadingMoreFilter();
        steps.inputEmailDossier("as.nanao@gmail.com");
        steps.applicationResults();

    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Disabled("Не работает")
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Сортировка персоны по связанным социальным сетям ")
    void DossierFilterBindedSnt() {
        steps.openDossier("Персоны");
        steps.loadingMoreFilter();
        steps.inputDossierBinSnt("key");
        steps.applicationResults();

    }

}
