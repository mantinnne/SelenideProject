package privat;

import Methud.privat.DossierSteps;
import com.codeborne.selenide.SelenideElement;
import config.BaseMessageSteps;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;


public class DossierPage extends BaseMessageSteps {
    String filterLastName = "Герасимов",
            filterName = "Александр";

    DossierSteps steps = new DossierSteps();

    SelenideElement dossierList = $(".profiles-list"),
            lastNameDossier = $("#last-name"),
            firstNameDossier = $("#first-name"),
            buttonSaveResult = $(".app-button"),
            bounceWait = $(".double-bounce2");


    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Открытие раздела персоны")
    void selectDossier() {
        steps.openDossier("Персоны");
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Сортировка персоны по фамилии")
    void DossierFilterLastName() {
        steps.openDossier("Персоны");
        steps.filterLastName(filterLastName);
        steps.checkResultFilter(filterLastName);
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Сортировка персоны по Имени")
    void DossierFilterName() {
        steps.openDossier("Персоны");
        steps.filterFirstName(filterName);
        steps.checkResultFilter(filterName);

    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Сортировка персоны по Имени и Фамилии")
    void DossierFilterNameAndLastName() {
        steps.openDossier("Персоны");
        steps.filterLastName(filterLastName);
        steps.filterFirstName(filterName);
        steps.checkResultFilter(filterLastName + " " + filterName);

    }
}
