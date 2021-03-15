package privat;

import Methud.privat.MessageSteps;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.BaseMessageSteps;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class MessagesPage extends BaseMessageSteps {

    MessageSteps steps = new MessageSteps();
    String nameSection = "Сообщения";

    SelenideElement message = $(".posts-table"),
            inputKeyWord = $("#keywords"),
            waitBounce = $(".double-bounce2"),
            messeageProfileFilterID = $("#profile-id"),
            messeageGroupFilterID = $("#group-id");


    @Test
    @Story("Messages")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Открытие раздела messages")
    void selectMessages() {
        steps.openMesseage(nameSection);
        steps.checkMesseageLoading();
    }

    @Test
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Проверка поиска сообщений по теме  у профилей")
    void selectMessagesForTheme() {
        steps.openMesseage(nameSection);
        steps.checkMesseageLoading();
        steps.selectForMesseage(5, 6, 0);
    }

    @Test
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Проверка поиска сообщений по направлений у профилям")
    void selectMessagesForDirection() {
        steps.openMesseage(nameSection);
        steps.checkMesseageLoading();
        steps.selectForMesseage(63, 64, 1);
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
        });
        step("Проверка отображения выделенного  ключевого слова на странице и цвет выделения", () -> {
            ElementsCollection detected = $$("detected").shouldHave(CollectionCondition.sizeNotEqual(0));
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
        steps.openMesseage(nameSection);
        steps.checkMesseageLoading();
        steps.selectMessage("source", 8);

    }

    @Test
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Проверка поиска сообщений по социальной сети")
    void selectMessageNetworkCosial() {
        steps.openMesseage(nameSection);
        steps.checkMesseageLoading();
        steps.selectMessage("group-snt", 9);

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
            $$("td > a").find(text("Страховецкая Ева")).shouldHave(visible);
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
            $$("td > span > a").find(text("12345")).shouldHave(visible);
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

