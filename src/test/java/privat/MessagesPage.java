package privat;

import Methud.privat.MessageSteps;
import com.codeborne.selenide.SelenideElement;
import config.BasePrivat;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class MessagesPage extends BasePrivat {

    MessageSteps steps = new MessageSteps();
    String nameSection = "Сообщения",
            keyWord = "Матрос";

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
        steps.checkDischargeOption(5, 6, 0);
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
        steps.checkDischargeOption(63, 64, 1);
        steps.selectForMesseage(63, 64, 1);
    }

    @Test
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Проверка поиска сообщений по ключевым словам")
    void selectMessagesKeyWord() {
        steps.openMesseage(nameSection);
        steps.checkMesseageLoading();
        steps.setVallKeyWord(keyWord);
        steps.checkKeyword(keyWord);

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
        steps.openMesseage(nameSection);
        steps.checkMesseageLoading();
        steps.messageIdFilterProfile("12345");
        steps.checkMessageIdFilterProfile();
    }


    @Test
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Проверка поиска сообщений по id группы")
    void selectMessagesIdFilterGroup() {
        steps.openMesseage(nameSection);
        steps.checkMesseageLoading();
        steps.inputIdGroup("12345");
        steps.checkMesseageGroup("12345");

    }


    @Test
    @Disabled("Сортировка сообщений по дате")
    @Story("Messages")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Проверка фильтрации сообщений по выбранной дате")
    void messeageDataFilter() {
        step("Открытие раздела Messeages", this::selectMessages);
        step("Установки фильтра даты по отправке сообщений", () -> {

        });

    }

}

