package config;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Set;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;
import static io.qameta.allure.Allure.step;

public class BaseOko {
    WebDriver driver;

    static Set<Cookie> cookie;


    static final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    static String name = "input[type='email']";
    static String password = "input[type='password']";
    static String submitBotton = "button[type='submit']";
    static String url = config.getUrlOko();

    static Cookie token_custom;
    static Cookie token_laravel_passport;
    static Cookie auth_strategy;
    static Cookie SL_wptGlobTipTmp = new Cookie("SL_wptGlobTipTmp", "1");
    static Cookie SL_GWPT_Show_Hide_tmp = new Cookie("SL_GWPT_Show_Hide_tmp", "1");

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
/*


    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true).enableLogs(BROWSER, ALL).includeSelenideSteps(false));
        Configuration.browser = config.getWebBrowser();
        Configuration.browserVersion = config.getVersionBrowser();
        Configuration.startMaximized = true;
        Configuration.timeout = 30_000;


        if (config.getRemoteUrl() != null) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
            Configuration.remote = config.getRemoteUrl();

        }

        step("Открытие сайта " + url, () -> open(url));
        step("Открытие сессии", () -> {
            $(name).val("tester@cism-ms.ru");
            $(password).val("id7@cS2jA");
            $(submitBotton).click();
            sleep(4000);
        });

        WebDriver driver = WebDriverRunner.getWebDriver();
        token_custom = driver.manage().getCookieNamed("auth._token.custom");
        token_laravel_passport = driver.manage().getCookieNamed("auth._token.laravel.passport");
        auth_strategy = driver.manage().getCookieNamed("auth.strategy");
        System.out.println(token_custom);
        System.out.println(token_laravel_passport);
        System.out.println(auth_strategy);

    }
    @BeforeEach
    void login() {
        open(url);
        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(token_custom);
        driver.manage().addCookie(token_laravel_passport);
        driver.manage().addCookie(auth_strategy);
*//*        driver.manage().addCookie(SL_wptGlobTipTmp);
        driver.manage().addCookie(SL_GWPT_Show_Hide_tmp);*//*
        sleep(1000);
        open(url+"heatmap/country");
        switchTo().window(0);

    }
    @AfterEach
    public void closeSession() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        attachVideo();
        Selenide.close();

    }*/

/*    @AfterAll
    public static void logoutSession() {
        step("Закрытие сессии", () -> {
            $(".logo > a").click();
            $("title").shouldHave(attribute("text", "ЦИСМ"));
            $(".user-info").click();
            $(byLinkText("Выйти")).click();
        });
        closeWebDriver();

    }*/
}