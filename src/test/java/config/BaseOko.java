package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.time.Duration;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class BaseOko {
    String mapTopPanel = ".map-bottom-bar-top-panel";
    String segmentBlock = "section[class='content-block segments-block']";
    String directionBlock = "section[class='content-block directions-stat-block']";
    String streamBlock = "section[class='content-block stream-block']";
    String name = "input[type='email']";
    String password = "input[type='password']";
    String submitBotton = "button[type='submit']";
    String url = "http://oko-stage.cism-ms.ru/";


    @BeforeAll

    public static void setUp() {
        setSelenideConfiguration();
        SelenideLogger.addListener("allure", new AllureSelenide());

    }

    @BeforeEach
    void login() {
        step("Открытие сайта " + url, () -> open(url));
        step("Открытие сессии", () -> {
            $(name).val("tester@cism-ms.ru");
            $(password).val("id7@cS2jA");
            $(submitBotton).click();
        });
        step("Проверка загрузки страницы", () -> {

            $("title").shouldHave(attribute("text", "ЦИСМ"));
            $(mapTopPanel).shouldBe(visible);
            $(segmentBlock).shouldBe(visible);
            $(directionBlock).shouldBe(visible);
            $(streamBlock).shouldBe(visible, Duration.ofSeconds(15));
        });
    }

    @AfterEach
    public void cleanSession() {

        step("Закрытие сессии", () -> {
            $(".logo").click();
            $(".user-info").click();
            $(byText("Выйти")).click();
        });


        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.close();
    }

    private static void setSelenideConfiguration() {
        Configuration.timeout = 15000;
        Configuration.startMaximized = true;

    }


}