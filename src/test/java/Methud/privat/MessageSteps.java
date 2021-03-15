package Methud.privat;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class MessageSteps {

    ElementsCollection messageListDirection = $$(".vs__dropdown-menu > li");

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

    @Step("Выбор значений фильтрации источнику сообщений")
    public void selectMessage(String idFilterInput, int size) {
        ElementsCollection sourceMessage = $$("#" + idFilterInput + "> option");
        sourceMessage.shouldHaveSize(size);
        for (SelenideElement element : sourceMessage) {
            element.click();
            waitBounce.shouldBe(hidden, Duration.ofSeconds(25));
            message.shouldBe(visible, Duration.ofSeconds(20));
        }
    }

    public void selectForMesseage(int size, int fullSize, int number) {
        SelenideElement messageInputDirection = $(".vs__selected-options", number);
        messageInputDirection.click();
        messageListDirection.shouldHaveSize(size);
        step("Выбор первого фильтра и проверка доступности сброса фильтров", () -> {
            messageListDirection.get(0).click();
            waitBounce.shouldBe(hidden, Duration.ofSeconds(25));
            messageInputDirection.click();
            messageListDirection.shouldHaveSize(fullSize);
        });
        step("Проверка доступности для выбора всех фильтров", () -> {
            for (int i = 1; i < messageListDirection.size(); i++) {
                messageListDirection.get(i).click();
                waitBounce.shouldBe(hidden, Duration.ofSeconds(25));
                messageInputDirection.click();
            }
            messageListDirection.get(0).click();
            waitBounce.shouldBe(hidden, Duration.ofSeconds(25));
        });
    }

}
