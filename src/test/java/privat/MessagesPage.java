package privat;

import Methud.Privat;
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
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class MessagesPage extends BasePrivat {
    Privat privat = new Privat();

    SelenideElement message = $(".posts-table"),
            inputKeyWord = $("#keywords"),
            waitBounce = $(".double-bounce2"),
            messeageProfileFilterID = $("#profile-id"),
            messeageGroupFilterID = $("#profile-id");


    @Test
    @Story("Messages")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Открытие раздела messages")
    void selectMessages() {
        step("Открытие раздела Messeages", () -> $(By.linkText("Сообщения")).click());
        step("Проверка отображения загрузки списка сообщений", () -> {
            message.shouldBe(visible);
        });
    }

    @Test
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Проверка поиска сообщений по теме  у профилей")
    void selectMessagesForTheme() {
        privat.selectForMesseage(5, 6, 0);
    }

    @Test
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Проверка поиска сообщений по направлений у профилям")
    void selectMessagesForDirection() {
        privat.selectForMesseage(62, 63, 1);
    }

    @Test
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Проверка поиска сообщений по ключевым словам")
    void selectMessagesKeyWord() {
        step("Открытие раздела Messeages", this::selectMessages);
        step("Проверка отображения загрузки списка сообщений", () -> message.shouldBe(visible));
        step("Ввод в поле поиска клчевого слова", () -> {
            inputKeyWord.val("Матрос");
            waitBounce.shouldBe(visible).shouldHave(disappear);
        });
        step("Проверка отображения выделенного  ключевого слова на странице и цвет выделения", () -> {
            ElementsCollection detected = $$("detected");
            for (SelenideElement element : detected) {
                element.shouldBe(visible);
                element.shouldHave(cssValue("background-color", "rgba(255, 255, 0, 1)"));
            }
        });
    }

    @Test
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Проверка поиска сообщений по источнику")
    void selectMessageSourceFilter() {
        privat.selectMessage("source", 8);

    }

    @Test
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Проверка поиска сообщений по социальной сети")
    void selectMessageNetworkCosial() {
        privat.selectMessage("group-snt", 9);

    }

    @Test
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Проверка поиска сообщений по id пользователя")
    void selectMessagesIdFilterProfile() {
        step("Открытие раздела Messeages", this::selectMessages);
        step("Проверка отображения загрузки списка сообщений", () -> message.shouldBe(visible));
        step("Ввод id пользователя, для проверки фильтрации по id пользователя", () -> messeageProfileFilterID.val("12345"));
        step("Проверка отображения сообщения от пользователя с указанным id", () -> {
            waitBounce.shouldHave(disappear, Duration.ofSeconds(20));
            $(byText("Страховецкая Ева")).shouldBe(visible);
        });
    }


    @Test
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Проверка поиска сообщений по id группы")
    void selectMessagesIdFilterGroup() {
        step("Открытие раздела Messeages", this::selectMessages);
        step("Проверка отображения загрузки списка сообщений", () -> message.shouldBe(visible));
        step("Ввод id группы, для проверки фильтрации по id группы", () -> messeageGroupFilterID.val("12345"));
        step("Проверка отображения сообщения от пользователя с указанным id", () -> {
            waitBounce.shouldHave(disappear, Duration.ofSeconds(20));
        });
    }

    @Test
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Сортировка сообщений по социальной сети ")
    void sortMessagesSocialNetwork() {
        step("Открытие раздела Messeages", this::selectMessages);
        step("Проверка отображения загрузки списка сообщений", () -> message.shouldBe(visible));
        step("Выборка сортировки по социальной сети", () -> {

        });
    }

    @Test
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Проверка фильтрации сообщений по выбранной дате")
    void messeageDataFilter() {
        step("Открытие раздела Messeages", this::selectMessages);
        step("Установки фильтра даты по отправке сообщений", () -> {

//            $("#publication-date-from");
//            sleep(2000);
        });

    }

}

