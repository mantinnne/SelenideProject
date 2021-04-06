package oko;

import Methud.Oko.RegionSteps;
import Methud.Oko.SchoolSteps;
import config.BaseOko;
import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class SchoolPage extends BaseOko {
    RegionSteps regionSteps = new RegionSteps();
    SchoolSteps schoolSteps = new SchoolSteps();

    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Medium")})
    @DisplayName("Поиск школы")
    void seacrhSchool() {
        regionSteps.selectRegion("Москва");
        regionSteps.checkSelectRegion("Москва");
        schoolSteps.selectSchoolForRegion();
        schoolSteps.inputNameSchoolIsSearch();
        schoolSteps.openDictrictVievSchool();
        schoolSteps.checkResultSearchSchools();

    }

    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Medium")})
    @DisplayName("Выбор школы")
    @RepeatedIfExceptionsTest(repeats = 3)
    void regionSelectSchool() {
        regionSteps.selectRegion("Москва");
        regionSteps.checkSelectRegion("Москва");
        schoolSteps.selectSchoolForRegion();
        schoolSteps.openDictrictVievSchool();
        schoolSteps.checkVievSchoolForDictrict();
        schoolSteps.findFirstSchoolOnList();
        schoolSteps.switchWindows(1);
        schoolSteps.checkLoadingSchools();

    }


    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Oko")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Low")})
    @DisplayName("Проверка выборки страницы школьников")
    void regionSelectPageSchool() {
        regionSteps.selectRegion("Москва");
        regionSteps.checkSelectRegion("Москва");
        schoolSteps.selectSchoolForRegion();
        schoolSteps.openDictrictVievSchool();
        schoolSteps.findFirstSchoolOnList();
        schoolSteps.switchWindows(1);
        schoolSteps.checkDisableNavigationForListSchoolboy();
        schoolSteps.selectSchoolboyPageTwo();
        schoolSteps.checkTwoPageSchoolboy();
    }


    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("High")})
    @DisplayName("Загрузки статистики по школе")
    void regionSelectStaticticSchool() {
        regionSteps.selectRegion("Москва");
        regionSteps.checkSelectRegion("Москва");
        schoolSteps.selectSchoolForRegion();
        schoolSteps.openDictrictVievSchool();
        schoolSteps.findFirstSchoolOnList();
        schoolSteps.switchWindows(1);
        schoolSteps.openStaticticForSchool();
        schoolSteps.checkOpenStaticticsSchool();
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Medium")})
    @DisplayName("Сортировка школьников")
    void regionSelectSortSchoolboy() {
        regionSteps.selectRegion("Москва");
        regionSteps.checkSelectRegion("Москва");
        schoolSteps.selectSchoolForRegion();
        schoolSteps.openDictrictVievSchool();
        schoolSteps.findFirstSchoolOnList();
        schoolSteps.switchWindows(1);
        schoolSteps.sortSchoolboy();
    }

    /*
        @RepeatedIfExceptionsTest(repeats = 3)
    */
    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Medium")})
    @DisplayName("Фильтрация школьников по направлениям")
    void regionSelectFilterSchoolboy() {
        regionSteps.selectRegion("Москва");
        regionSteps.checkSelectRegion("Москва");
        schoolSteps.selectSchoolForRegion();
        schoolSteps.openDictrictVievSchool();
        schoolSteps.findFirstSchoolOnList();
        schoolSteps.switchWindows(1);
        schoolSteps.filterSchoolboy();

    }
}

