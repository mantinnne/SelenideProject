package config;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;
import static io.qameta.allure.Allure.step;

public class BaseOko {

    static String name = "input[type='email']";
    static String password = "input[type='password']";
    static String submitBotton = "button[type='submit']";
    static String url = "http://oko-stage.cism-ms.ru/";


    @BeforeAll
    public static void setup()  {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Configuration.browser = System.getProperty("browser", "chrome");
        capabilities.setCapability("browserVersion", "88.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "http://10.191.1.51:4444/wd/hub/";
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.timeout = 25000;
        Configuration.startMaximized = true;
        Configuration.pageLoadTimeout = 60000;
    }


    @BeforeEach
    void login() {
        step("Открытие сайта " + url, () -> open(url));
        step("Открытие сессии", () -> {
            $(name).val("tester@cism-ms.ru");
            $(password).val("id7@cS2jA");
            $(submitBotton).click();
        });
    }

    @AfterEach
    public void cleanSession() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        attachVideo();

        step("Закрытие сессии", () -> {
            $(".logo > a").click();
            $("title").shouldHave(attribute("text", "ЦИСМ"));
            $(".user-info").click();
            $(byLinkText("Выйти")).click();
        });
        closeWebDriver();

    }


}