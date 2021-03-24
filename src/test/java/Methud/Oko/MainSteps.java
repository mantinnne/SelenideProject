package Methud.Oko;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static io.qameta.allure.Allure.step;

public class MainSteps {
    final String
            zoomIn = "button[title='Zoom in']",
            zoomOut = "button[title='Zoom out']";

    SelenideElement mapTopPanel = $(".map-bottom-bar-top-panel"),
            segmentBlock = $("section[class='content-block segments-block']"),
            directionBlock = $("section[class='content-block directions-stat-block']"),
            streamBlock = $("section[class='content-block stream-block']");

    ElementsCollection direction = $(".directions-list").$$(".direction-item");


    @Step("Проверка загрузки главной страницы и информации по всем регионам")
    public MainSteps loadingPageMain() {
        $("title").shouldHave(attribute("text", "ЦИСМ"));
        mapTopPanel.shouldBe(visible, Duration.ofSeconds(40));
        segmentBlock.shouldBe(visible, Duration.ofSeconds(40));
        directionBlock.shouldBe(visible, Duration.ofSeconds(40));
        streamBlock.shouldBe(visible, Duration.ofSeconds(40));

        return this;
    }

    @Step("Проверка возможности выбрать направления для фильтрации")
    public MainSteps selectDirection() {
        direction.shouldHaveSize(5);
        for (SelenideElement selenideElement : direction) {
            selenideElement.click();
            selenideElement.shouldHave(cssClass("active"));
            $$x("//div[@class='title']").shouldHaveSize(1);
        }
        return this;
    }

    @Step("Увелечение и уменьшение зума у карты")
    public MainSteps zoom() {
        step("Увелечение зума у карты", () -> $(zoomIn).click());
        step("Увелечение зума у карты", () -> $(zoomOut).click());
        return this;
    }
}
