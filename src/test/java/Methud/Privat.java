package Methud;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class Privat {

    SelenideElement message = $(".posts-table");
    SelenideElement waitBounce = $(".double-bounce2");
    ElementsCollection messageListDirection = $$(".vs__dropdown-menu > li");


    public void selectMessage(String idFilterInput, int size) {
        step("Открытие раздела Messeages", () -> $(By.linkText("Сообщения")).click());
        step("Проверка отображения загрузки списка сообщений", () -> message.shouldBe(visible));
        step("Выбор значения фильтрация источника сообщений", () -> {
            ElementsCollection sourceMessage = $$("#" + idFilterInput + "> option");

            sourceMessage.shouldHaveSize(size);
            for (SelenideElement element : sourceMessage) {
                element.click();
                waitBounce.shouldBe(visible).shouldHave(disappear, Duration.ofSeconds(20));
            }
        });
    }

    public void selectForMesseage(int size, int fullSize, int number) {
        SelenideElement messageInputDirection = $(".vs__selected-options", number);

        step("Открытие раздела Messeages", () -> $(By.linkText("Сообщения")).click());
        step("Нажатие на поле с выбором полей для фильтрации", (Allure.ThrowableRunnableVoid) messageInputDirection::click);
        step("Проверка количетва доступных для выбора фильтров", () -> {
            messageListDirection.shouldHaveSize(size);
        });
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
