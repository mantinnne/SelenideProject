package Methud.privat;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class MessageSteps {

    ElementsCollection messageListDirection = $$(".vs__dropdown-menu > li"),
            messageListSnt = $$("#group-snt > option");

    SelenideElement message = $(".posts-table"),
            inputKeyWord = $("#keywords"),
            waitBounce = $(".double-bounce2"),
            messeageProfileFilterID = $("#profile-id"),
            messeageGroupFilterID = $("#group-id");

    @Step("Открытие раздела -> {0}")
    public MessageSteps openMesseage(String linkTest) {
        $(By.linkText(linkTest)).click();
        message.shouldBe(visible);
        return this;
    }

    @Step("Проверка отображения загрузки списка сообщений")
    public MessageSteps checkMesseageLoading() {
        message.shouldBe(visible);
        return this;
    }

    @Step("Выбор значений фильтрации сообщений по -> {0}")
    public void selectMessage(String idFilterInput, int size) {
        ElementsCollection sourceMessage = $$("#" + idFilterInput + "> option");
        sourceMessage.shouldHaveSize(size);
        for (SelenideElement element : sourceMessage) {
            element.click();
            waitBounce.shouldBe(hidden, Duration.ofSeconds(25));
            message.shouldBe(visible, Duration.ofSeconds(20));
        }
    }

    @Step("Ввод ключевого слова -> {0} для поиска данного слова")
    public MessageSteps setVallKeyWord(String keyWord) {
        inputKeyWord.val(keyWord);
        return this;
    }

    @Step("Проверка отображения ключевого слова -> {0} на странице")
    public MessageSteps checkKeyword(String keyWord) {
        ElementsCollection detected = $$("detected").shouldHave(CollectionCondition.sizeNotEqual(0));
        for (SelenideElement element : detected) {
            element.shouldBe(visible);
            element.shouldHave(cssValue("background-color", "rgba(255, 255, 0, 1)"));
        }
        return this;
    }

    @Step("Поиска сообщений по id пользователю -> {0}")
    public MessageSteps messageIdFilterProfile(String profileID) {
        messeageProfileFilterID.val("12345");
        $$("td > a").find(text("Страховецкая Ева")).shouldHave(visible);
        return this;
    }

    @Step("Проверка сообщения от указанного пользователя")
    public MessageSteps checkMessageIdFilterProfile() {
        $$("td > a").find(text("Страховецкая Ева")).shouldHave(visible);
        return this;
    }

    @Step("Проверка отображения сброса при выборе фильтра")
    public MessageSteps checkDischargeOption(int size, int fullSize, int number) {
        SelenideElement messageInputDirection = $(".vs__selected-options", number);
        messageInputDirection.click();
        messageListDirection.shouldHaveSize(size);
        step("Выбор первого фильтра и проверка доступности сброса фильтров", () -> {
            messageListDirection.get(0).click();
            waitBounce.shouldBe(hidden, Duration.ofSeconds(25));
            messageInputDirection.click();
            messageListDirection.shouldHaveSize(fullSize);
        });
        return this;
    }

    @Step("Проверка доступности для выбора всех фильтров")
    public MessageSteps selectForMesseage(int size, int fullSize, int number) {
        SelenideElement messageInputDirection = $(".vs__selected-options", number);
        for (int i = 1; i < messageListDirection.size(); i++) {
            messageListDirection.get(i).click();
            waitBounce.shouldBe(hidden, Duration.ofSeconds(25));
            messageInputDirection.click();
        }
        messageListDirection.get(0).click();
        waitBounce.shouldBe(hidden, Duration.ofSeconds(25));
        return this;
    }

    @Step("Ввод id -> {0} группы в поле для фильтрации поиска сообщений от группы")
    public MessageSteps inputIdGroup(String idGroup) {
        messeageGroupFilterID.val(idGroup);
        return this;
    }

    @Step("Проверка сообщения от выбранной группы c id -> {0}")
    public MessageSteps checkMesseageGroup(String idGroup) {
        $$("td > span > a").find(text(idGroup)).shouldHave(visible);
        return this;
    }
}
