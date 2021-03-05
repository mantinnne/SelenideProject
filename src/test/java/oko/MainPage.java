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

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class MainPage extends BaseOko {
    final String directionList = ".directions-list",
            directionItem = ".direction-item",
            zoomIn = "button[title='Zoom in']",
            zoomOut = "button[title='Zoom out']";

    String mapTopPanel = ".map-bottom-bar-top-panel";
    String segmentBlock = "section[class='content-block segments-block']";
    String directionBlock = "section[class='content-block directions-stat-block']";
    String streamBlock = "section[class='content-block stream-block']";

    @Test
    void waitLoadingMainPage() {
        step("Проверка загрузки страницы", () -> {
            $("title").shouldHave(attribute("text", "ЦИСМ"));
            $(mapTopPanel).shouldBe(visible);
            $(segmentBlock).shouldBe(visible);
            $(directionBlock).shouldBe(visible);
            $(streamBlock).shouldBe(visible, Duration.ofSeconds(15));
        });

    }

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
