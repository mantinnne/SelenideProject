package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.time.Duration;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static io.qameta.allure.Allure.step;

public class BaseOko {
    static String mapTopPanel = ".map-bottom-bar-top-panel";
    static String segmentBlock = "section[class='content-block segments-block']";
    static String directionBlock = "section[class='content-block directions-stat-block']";
    static String streamBlock = "section[class='content-block stream-block']";
    static String name = "input[type='email']";
    static String password = "input[type='password']";
    static String submitBotton = "button[type='submit']";
    static String url = "http://oko-stage.cism-ms.ru/";


        @BeforeAll
    public static void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "89.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "http://10.191.1.51:4444/wd/hub/";
    }


    @BeforeEach
    void login() {
        setSelenideConfiguration();

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

        Selenide.close();
    }

    private static void setSelenideConfiguration() {
        addListener("allure", new AllureSelenide());
        Configuration.timeout = 15000;
        Configuration.startMaximized = true;

    }


}