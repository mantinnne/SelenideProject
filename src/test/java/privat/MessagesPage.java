package privat;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.BasePrivat;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class MessagesPage extends BasePrivat {
    ElementsCollection messageListDirection = $$("#vs6__listbox > li");

    SelenideElement message = $(".posts-table"),
            messageInputDirection = $(".vs__selected-options");


    @Test
    @Story("Messages")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Открытие раздела messages")
    void selectMessages() {
        step("Открытие раздела Messeages", () -> $(By.linkText("Сообщения")).click());
        step("Проверка отображения загрузки списка сообщений", () -> {
            $(message).shouldBe(visible);
        });
    }

    @Test
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Открытие раздела messages")
    void selectMessagesForDirection() {
        step("Открытие раздела Messeages", this::selectMessages);
        step("Открытие выбора направления для фильтрации", () -> {
            messageInputDirection.click();
        });
        step("Открытие выбора направления для фильтрации", () -> {
            messageListDirection.shouldHaveSize(5);
        });
        step("Выбор первого направления и проверка доступности сброса фильтров", () -> {
            messageListDirection.get(0).click();
            $(".double-bounce2").shouldBe(disappear);
            messageInputDirection.click();
            messageListDirection.shouldHaveSize(6);
        });
        step("Проверка доступности для выбора всех направлений", () -> {
            for (int i = 1; i < 6; i++) {
                messageListDirection.get(i).click();
                $(".double-bounce2").shouldBe(hidden, Duration.ofSeconds(15));
                messageInputDirection.click();
            }
        });
    }

    @Test
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Открытие раздела messages")
    void messeageDataFilter() {
        step("Открытие раздела Messeages", this::selectMessages);
        step("Установки фильтра даты по отправке сообщений", () -> {
            $("#publication-date-from").val("12121994");
        });

    }
}

