package oko;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.BaseOko;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class MainPage extends BaseOko {


    final String directionList = ".directions-list",
            directionItem = ".direction-item",
            zoomIn = "button[title='Zoom in']",
            zoomOut = "button[title='Zoom out']";
    SelenideElement region = $("button[class='region-picker-button app-button filled size-md']"),
            cityForRegion = $(".cities-toggle");


    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Oko"), @Tag("Medium")})
    @DisplayName("Сортировка по направлениям")
    void selectDirection() {

        step("Выбор направления по регионам и проверка доступных регионов", () -> {
            ElementsCollection direction = $(directionList).$$(directionItem)
                    .shouldHaveSize(5);
            step("Перебор регионов для отображения", () -> {
                for (SelenideElement selenideElement : direction) {
                    selenideElement.click();
                    step("проверка выборки направления и получение класса active у выбранного направления", () -> {
                        $(selenideElement).shouldHave(cssClass("active"));
                        $$x("//div[@class='title']").shouldHaveSize(1);
                    });
                }
            });
        });


    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Oko"), @Tag("High")})
    @DisplayName("Выборка регионов для оторажения региона")
    void selectRegionView() {

        step("Проверка отображения текста у кнопки выборки регионов", () -> {
            region.shouldHave(text("Вся Россия"));
        });
        step("Нажатие на кнопку выбора регионов", () -> region.click());
        step("Проверка оторажение текста заголовка h2", () -> {
            $x("//h2").shouldHave(text("Выберите регион для отображения"));
        });
        step("Нахждение в списках Адыгеи и выборка его", () -> $(byText("Адыгея")).click());
        step("Проверка отображения название региона", () -> {
            $(".region-name").shouldHave(text("Адыгея"));

        });
        step("Открытие дополнительных городов по данному региону", () -> cityForRegion.click());
        step("Проверка, о раскрытии всех городов", () -> {
            cityForRegion.shouldHave(text("Свернуть"));
        });
    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Oko"), @Tag("Low")})
    @DisplayName("Увелечение и уменьшение зума у карты")
    void zoom() {
        step("Увелечение зума у карты", () -> $(zoomIn).click());
        sleep(1000);
        step("Увелечение зума у карты", () -> $(zoomOut).click());
        sleep(1000);
    }
}
