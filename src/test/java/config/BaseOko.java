package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;
import java.util.Map;

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

    @BeforeEach
    @Order(1)
    void setup() {
        Configuration.remote = "http://selenoid:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "89.0");
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        setSelenideConfiguration();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }


    @BeforeEach
    @Order(2)
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