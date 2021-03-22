package oko;

import Methud.Oko.RegionSteps;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.BaseOko;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static io.qameta.allure.Allure.step;

public class RegionPage extends BaseOko {

    RegionSteps steps = new RegionSteps();
    MainPage mainPage = new MainPage();
    ElementsCollection navigationTabSelect = $$x("//li[@class='tab']/a");


    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("medium")})
    @DisplayName("Проверка отображения текста заголовка, при фильтрации регионов")
    void checkFilterRegion() {
        steps.checkFilterRegionNameButton();
        steps.clickButtonFilterRegion();
        steps.checkTextFilterRegion();
    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("medium")})
    @DisplayName("Выбор региона")
    void selectRegionView() {
        steps.selectRegion("Москва");
        steps.checkSelectRegion("Москва");
    }

    @Test
    void regionMoreCityLoading() {
        steps.selectRegion("Адыгея");
        steps.checkSelectRegion("Адыгея");
        steps.openMoreCityForRegion();
        steps.checkOpenMoreCityForRegion();
    }

    @Test
    @Disabled("Требуется доработка прокси")
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("medium")})
    @DisplayName("Выгрузка pdf репорта по региону")
    void regionDownloadReport() throws FileNotFoundException {
        steps.selectRegion("Москва");
        steps.checkSelectRegion("Москва");
        steps.downloadReportForRegion();
    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("medium")})
    @DisplayName("Отображение школ или университетов на карте")
    void regionMappingControl() {
        steps.selectRegion("Москва");
        steps.checkSelectRegion("Москва");
        steps.selectVievSchoolAndUniversity();
    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("medium")})
    @DisplayName("Выбор направления по регионам")
    void regionSelectDirection() {
        step("Выбор направления по регионам и проверка доступных регионов", () -> mainPage.checkSelectDirection());
    }


    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("medium")})
    @DisplayName("Скрытие отображения панели статистики по региону")
    void regionSidebarControlHidden() {
        steps.selectRegion("Москва");
        steps.checkSelectRegion("Москва");
        steps.hiddenPanelStaticticForRegion();
    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("medium")})
    @DisplayName("Открытие отображения панели статистики по региону")
    void regionSidebarControlOpen() {
        steps.selectRegion("Москва");
        steps.checkSelectRegion("Москва");
        steps.hiddenPanelStaticticForRegion();
        steps.openPanelStaticticForRegion();
    }


    @Test
    @Story("Oko")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("low")})
    @DisplayName("Проверка отображения разделов  навигации по региону")
    void setNavigationTab() {
        steps.selectRegion("Москва");
        steps.checkSelectRegion("Москва");
        for (SelenideElement selenideElement : navigationTabSelect) {
            String selenideElementText = selenideElement.getText();
            step("Выбор раздела региона: -> " + selenideElementText, () -> {
                selenideElement.click();
                step("Проверка появления класса active у выбранного раздела навигации: " + selenideElementText, () -> {
                    $(selenideElement).shouldHave(cssClass("active"));
                });
                step("Проверка загрузки разделов информации по разделу: ->" + selenideElementText, () -> {
                    switch (selenideElementText) {
                        case "Статистика":
                            $(".map-sidebar-statistic").shouldBe(visible);
                            break;
                        case "Школы":
                            $(".group-panel").shouldBe(visible);
                            break;
                        case "Университеты":
                            $(".sidebar-content").shouldBe(visible);
                            break;
                        case "О городе":
                            $(".population-info").shouldBe(visible);
                            $(".governments").shouldBe(visible);
                            break;
                    }
                });
            });
        }
    }
}


