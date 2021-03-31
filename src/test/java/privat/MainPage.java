package privat;

import Methud.privat.MainSteps;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.BasePrivat;
import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class MainPage extends BasePrivat {

    MainSteps steps = new MainSteps();

    final ElementsCollection elements = $$(".values-count"),
            profileListViev = $$(".profile-info-container"),
            trafficValues = $$(".values-count"),
            filterChechBox = $$("label.checkbox-label"),
            directionList = $$x("//ul[@id='vs2__listbox']//li"),
            cosialNetworkList = $$x("//ul[@id='vs3__listbox']//li"),
            filterList = $$x("//ul[@id='vs1__listbox']//li"),
            metodicList = $$x("//ul[@id='vs4__listbox']//li"),
            countGroupList = $$x("//ul[@id='vs5__listbox']//li");


    final SelenideElement profilesAggRatings = $(".profiles-agg-ratings"),
            region = $("div#vs1__combobox"),
            button = $(".app-button"),
            direction = $("div#vs2__combobox"),
            cosialNetwork = $("div#vs3__combobox"),
            metodic = $("div#vs4__combobox"),
            minRating = $("#min-range"),
            maxRating = $("#max-range"),
            textArea = $("textarea"),
            countGroup = $("div#vs5__combobox"),
            buttonSave = $(withText("Сохранить"));


    @Story("Privat")
    @RepeatedIfExceptionsTest(repeats = 3, exceptions = NoSuchElementException.class)
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Загрузка допольнительных профилей на странице")
    void loadingMoreProfile() {
        steps.clickButtonLoading();
        steps.checkLoadingProfile();
    }


    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка отображение загрузки светофора профилей и проверка их неравенства null")
    void profileTrafficlight() {
        steps.loadingTrafficLight();
        steps.checkTrafficValue();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка доступности выборки фильтров забаненные/удаленные")
    void checkFilterDeletedOrBanProfile() {
        steps.checkFilterDeletedOrBan();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка фильтрации профилей по региону")
    void regionFilterProfile() {
        steps.checkResetFilter(region, filterList, 85, 86);
        steps.selectAllFilter(region, filterList);
        steps.resetFilterSelected(region, filterList);
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка фильтрации профилей по id профиля")
    void idFilterProfile() {
        steps.inputFirstIdProfile();
        steps.applyResult();
        steps.checkSearchProfileRelult(1);
        steps.resetFilterId();
        steps.SearcSomeProfileResult(6);
        steps.applyResult();
        steps.checkSearchProfileRelult(6);
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка фильтрации профилей напарвлениям профиля и по количеству групп в направлении")
    void directionFilterProfile() {
        steps.checkResetFilter(direction, directionList, 45, 46);
        steps.selectDirection();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка фильтрации профилей по социальной  сети")
    void cosialNetworkFilterProfile() {
        steps.selectFilter(cosialNetwork);
        steps.checkResetFilter(cosialNetwork, cosialNetworkList, 5, 6);
        steps.selectAllFilter(cosialNetwork, cosialNetworkList);
        steps.resetFilterSelected(cosialNetwork, cosialNetworkList);

    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка фильтрации профилей по методикам и рейтингу")
    void metodifFilterProfile() {
        steps.selectFilter(metodic);
        steps.checkResetFilter(metodic, metodicList, 2, 3);
        step("Выбор рейтинга в фильтре по минимальному значению", () -> {
            actions().moveToElement(minRating).clickAndHold().perform();
            actions().moveToElement(minRating, 0, 100).release().perform();
        });
        step("Выбор рейтинга в фильтре по макимальному  значению", () -> {
            actions().moveToElement(maxRating).clickAndHold().perform();
            actions().moveToElement(maxRating, 0, -100).release().perform();
        });
        steps.selectAllFilter(metodic, metodicList);
        steps.resetFilterSelected(metodic, metodicList);
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Low")})
    @DisplayName("Скрытие фильтров")
    void hiddenFilter() {
        steps.hiddenFilter();
        steps.checkHiddenFilter();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Быстрый поиск профилей")
    void fastSeachProfileFilter() {

        steps.selectFastSeachProfile();
        steps.generationIdProfile();
        steps.applyResult();
        steps.waitResultSearchProfile();

    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Быстрый поиск профилей")
    void fastSeachProfileFilterAddProfileIsNotBase() {
        steps.selectFastSeachProfile();
        steps.generationIdProfile();
        steps.applyResult();
        steps.waitResultSearchProfile();
        steps.checkAddProfileIsNotBase();
    }
}
