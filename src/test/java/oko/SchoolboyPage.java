package oko;

import Methud.Oko.RegionSteps;
import Methud.Oko.SchoolSteps;
import Methud.Oko.SchoolboySteps;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.BaseOko;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SchoolboyPage extends BaseOko {

    RegionSteps regionSteps = new RegionSteps();
    SchoolSteps schoolSteps = new SchoolSteps();
    SchoolboySteps schoolboySteps = new SchoolboySteps();

    SchoolPage schoolPage = new SchoolPage();
    ElementsCollection schoolLis = $$(".app-inline-link > a"),
            tab = $$(".tab"),
            dissierSchoolBoyList = $$(".link-container >a");

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Medium")})
    @DisplayName("Выбор личного профиля  школьника")
    void schoolboySelectDossier() {
        regionSteps.selectRegion("Москва");
        regionSteps.checkSelectRegion("Москва");
        schoolSteps.selectSchoolForRegion();
        schoolSteps.openDictrictVievSchool();
        schoolSteps.checkVievSchoolForDictrict();
        schoolSteps.findFirstSchoolOnList();
        schoolSteps.switchWindows(1);
        schoolSteps.checkLoadingSchools();
        schoolboySteps.selectFirstSchoolboy();

    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Medium")})
    @DisplayName("Просмотр данных доступных по школьнику")
    void schoolboyCheckInfo() {
        schoolboySelectDossier();
        schoolboySteps.openInfoSchoolboy();
    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Medium")})
    @DisplayName("Проверка выбора методики у профиля во вкладке сетевая активность")
    void profileSchoolboyCismCosialProfileFilterMetodic() {
        step("Выбор профиля школьника", this::schoolboySelectDossier);
        tab.get(1).click();
        step("Выборка методики у профиля для отображения", () -> {
            ElementsCollection collection = $$(".methodic__select-methodic > option").shouldHaveSize(3);
            for (int i = 1; i < collection.size(); i++) {
                collection.get(i).click();
                $(".methodic").shouldBe(visible);
            }
        });
    }
}
