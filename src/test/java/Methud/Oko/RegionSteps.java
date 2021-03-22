package Methud.Oko;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class RegionSteps {
    SelenideElement region = $("button[class='region-picker-button app-button filled size-md']"),
            cityForRegion = $(".cities-toggle");


    ElementsCollection layerControlItem = $(".map-layers-control").$$(".control-item");

    @Step("Проверка содержания  текста у кнопки выбора региона")
    public RegionSteps checkFilterRegionNameButton() {
        region.shouldHave(text("Вся Россия"));
        return this;
    }

    @Step("Нажатие на кнопку выбора регионов")
    public RegionSteps clickButtonFilterRegion() {
        region.click();
        return this;
    }

    @Step("Проверка оторажение текста заголовка выборки регионов")
    public RegionSteps checkTextFilterRegion() {
        $(".header-block > h2").shouldHave(text("Выберите регион для отображения"));
        return this;
    }

    @Step("Выбор региона: {0} ")
    public RegionSteps selectRegion(String nameRegion) {
        region.click();
        $(byLinkText(nameRegion)).click();
        return this;
    }

    @Step("Проверка выбранного региона {0}")
    public RegionSteps checkSelectRegion(String regionName) {
        $(".region-name").shouldHave(text(regionName));
        return this;
    }

    @Step("Открытие дополнительных городов по  региону")
    public RegionSteps openMoreCityForRegion() {
        cityForRegion.click();

        return this;
    }

    @Step("Проверка раскрытия всех городв по региону")
    public RegionSteps checkOpenMoreCityForRegion() {
        cityForRegion.shouldHave(text("Свернуть"));
        return this;
    }

    @Step("Скачивание репорта по региону")
    public RegionSteps downloadReportForRegion() throws FileNotFoundException {
        $(".pdf-print").download();
        return this;
    }

    @Step("Включение отображение школ и университетов")
    public RegionSteps selectVievSchoolAndUniversity() {
        for (SelenideElement selenideElement : layerControlItem) {
            selenideElement.click();
            step("Проверка добавление класса у выбранного пункта для отображения (школы или университеты)", () -> {
                $(selenideElement).shouldHave(cssClass("active"));
                selenideElement.click();
            });
        }
        return this;
    }

    @Step("Скрытие отображения панели статистики по региону")
    public RegionSteps hiddenPanelStaticticForRegion() {
        $(".sidebar-showed-control").click();
        $("section.map-sidebar-header").shouldHave(cssClass("sidebar-hidden"));
        return this;
    }

    @Step("Открытие отображения панели статистики по региону")
    public RegionSteps openPanelStaticticForRegion() {
        $(".sidebar-showed-control").click();
        $("section.map-sidebar-header").shouldNotHave(cssClass("sidebar-hidden"));
        return this;
    }

}
