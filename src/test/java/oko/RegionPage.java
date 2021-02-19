package oko;

import com.codeborne.selenide.DownloadOptions;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.SelenideElement;
import config.BaseOko;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RegionPage extends BaseOko {
    MainPage mainPage = new MainPage();
    final String periodList = ".date-range-list",
            periodItem = ".date-range-item",
            periodOlD = "//div[@class='mx-calendar-content']//table[3]//td",
            periodNew = "(//div[@class='mx-calendar-content'])[2]//table[3]//td[3]",
            navigationTab = "//li[@class='tab']/a",
            layerMappingControl = ".map-layers-control",
            LayerMappingControlItem = ".control-item";
    SelenideElement siderBarStatictic = $(".map-sidebar-statistic");

    ElementsCollection layerControlItem = $(layerMappingControl).$$(LayerMappingControlItem),
            perdiodListViev = $(periodList).$$(periodItem);


    static void selectRegion() {
        $(byLinkText("Москва")).shouldNotHave(disabled).click();
        switchTo().window(1);
        $(".region-name").shouldHave(text("Москва"));

        // выбор региона
    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("medium")})
    @DisplayName("Выгрузка pdf репорта по региону")
    void regionDownloadReport() {
        step("Выборка региона", RegionPage::selectRegion);
        step("Скачивание репорта по региону", () -> {
            $(".pdf-print").download(DownloadOptions.using(FileDownloadMode.FOLDER));
        });
    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("medium")})
    @DisplayName("Отображение школ или университетов на карте")
    void regionMappingControl() {
        step("Выборка региона", RegionPage::selectRegion);

        step("Включение отображение школ и университетов", () -> {
            for (SelenideElement selenideElement : layerControlItem) {
                selenideElement.click();
                step("Проверка добавление класса у выбранного пункта для отображения (школы или университеты)", () -> {
                    $(selenideElement).shouldHave(cssClass("active"));
                    sleep(1000);
                    selenideElement.click();
                });
            }
        });
    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("medium")})
    @DisplayName("Выбор направления по регионам")
    void regionSelectDirection() {
        step("Выборка региона", RegionPage::selectRegion);
        step("Выбор направления по регионам и проверка доступных регионов", () -> mainPage.selectDirection());
    }


    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("medium")})
    @DisplayName("Выборка периода для отображения статистики по региону")
    void regionSelectPeriod() {
        step("Выбока региона", RegionPage::selectRegion);
        step("Проверка доступных для выбора регионов", () -> perdiodListViev.shouldHaveSize(5));
        step("Перебор периода по регионам", () -> {
            for (SelenideElement selenideElement : perdiodListViev) {
                selenideElement.click();
                step("Проверка отображения статистики при выборе периода", () -> siderBarStatictic.shouldBe(visible));

                step("Проверка отображения текта у элементов и проверка добавление класса active при выборе", () -> {
                    String textField = selenideElement.getText();
                    if ((textField.equals("Неделя")) || (textField.equals("Месяц")) || (textField.equals(" Квартал")) || (textField.equals("Полгода"))) {
                        selenideElement.shouldBe(cssClass("active"));
                    }
                });
            }
        });
        step("Выбор собственного периода", () -> {
            $x(periodOlD).click();
            $x(periodNew).click();
            step("Проверка скрытия отображения для выбора таблицы периода", () -> $(".mx-range-wrapper").shouldBe(disappear));
        });
    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("medium")})
    @DisplayName("Скрытие отображения панели статистики по региону")
    void regionSidebarControl() {
        step("Выбока региона", RegionPage::selectRegion);
        step("Скрытие панели и открытие ее", () -> {
            for (int i = 0; i < 2; i++) {
                $(".sidebar-showed-control").click();
                sleep(500);
            }
        });
    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("low")})
    @DisplayName("Проверка отображения разделов  навигации по региону")
    void setNavigationTab() {
        step("Выбока региона", RegionPage::selectRegion);
        step("Перебор значений навигации листов", () -> {
            ElementsCollection navigationTabSelect = $$x(navigationTab);
            for (SelenideElement selenideElement : navigationTabSelect) {
                selenideElement.click();
                step("Проверка появления класса active у выбранного раздела навигации", () -> {
                    $(selenideElement).shouldHave(cssClass("active"));
                });
                step("Получение значения текстового поля у выбранного раздела", () -> {
                    String selenideElementText = selenideElement.getText();
                    step("Проверка загрузки разделов навигации", () -> {
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
        });
    }

}

