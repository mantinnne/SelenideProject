package config;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
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

    static final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    static String name = "input[type='email']";
    static String password = "input[type='password']";
    static String submitBotton = "button[type='submit']";
    static String url = config.getUrlOko();
/*    Map<String, String> cookiesAuthMain = new TakeToken().main();
    Map<String, String> cookiesAuthCore = new TakeTokenCore().main();*/


    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.browser = config.getWebBrowser();
        Configuration.browserVersion = config.getVersionBrowser();
        Configuration.startMaximized = true;
        Configuration.timeout = 30000;


        if (config.getRemoteUrl() != null) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
            Configuration.remote = config.getRemoteUrl();

        }
    }

    @BeforeEach
    void login() {/*
        WebDriverRunner.getWebDriver().manage().addCookie((Cookie) cookiesAuthCore);
        WebDriverRunner.getWebDriver().manage().addCookie((Cookie) cookiesAuthMain);*/
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