package oko;

import Methud.Oko.RegionSteps;
import Methud.Oko.SchoolSteps;
import Methud.Oko.SchoolboySteps;
import com.codeborne.selenide.ElementsCollection;
import config.BaseOko;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
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
        schoolboySelectDossier();
        schoolboySteps.selectProfileVievSchoolboy();
        schoolSteps.selectSection("Сетевая активность");
        schoolboySteps.navigationActiv();
    }
}
