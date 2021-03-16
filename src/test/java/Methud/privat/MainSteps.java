package Methud.privat;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MainSteps {
    String dropdownSelected = "vs__dropdown-option--selected";


    final ElementsCollection elements = $$(".values-count"),
            profileListViev = $$(".profile-info-container"),
            trafficValues = $$(".values-count"),
            filterChechBox = $$("label.checkbox-label"),
            filterList = $$x("//ul[@id='vs1__listbox']//li");


    final SelenideElement profilesAggRatings = $(".profiles-agg-ratings"),
            region = $("div#vs1__combobox");


    @Step("Нажатие на кнопку загрузки дополнительных профилей")
    public MainSteps clickButtonLoading() {
        $(".button-load-more").click();
        return this;
    }

    @Step("Проверка отображения количества профилей на странице")
    public MainSteps checkLoadingProfile() {
        profileListViev.shouldHaveSize(20);
        return this;
    }

    @Step("Проверка отображение загрузки светофора профилей")
    public MainSteps loadingTrafficLight() {
        profilesAggRatings.shouldBe(visible, Duration.ofSeconds(15));
        return this;
    }

    @Step("Проверка значения у светофоров")
    public MainSteps checkTrafficValue() {
        for (SelenideElement element : trafficValues) {
            String text = element.getText();
            assertNotEquals(null, text);
        }
        return this;
    }

    @Step("Проверка доступности выборки фильтров забаненные/удаленные")
    public MainSteps checkFilterDeletedOrBan() {
        for (SelenideElement chechBox : filterChechBox) {
            chechBox.click();
        }
        return this;
    }

    @Step("Выбор фильтра по региону и проверка появления возможности  сбросить фильтр")
    public MainSteps checkResetFilter() {
        region.click();
        filterList.shouldHaveSize(85).get(1).click();
        region.click();
        filterList.shouldHaveSize(86);

        return this;
    }

    @Step("Перебор значений фильтрации по региону")
    public MainSteps selectAllFilterRegion() {
        for (int i = 1; i < filterList.size(); i++) {
            filterList.get(i).click();
            profileListViev.get(1).shouldBe(visible);
            region.click();
            $(filterList.get(i)).shouldHave(cssClass(dropdownSelected));
            region.click();
        }
        return this;
    }


    @Step("Сброс фильтра")
    public MainSteps resetFilterSelected() {
        region.click();
        filterList.get(0).click();
        return this;
    }
}
