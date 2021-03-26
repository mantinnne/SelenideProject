package config;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;
import static io.qameta.allure.Allure.step;

public abstract class BasePrivat {

    static final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    String name = "input[type='email']";
    String password = "input[type='password']";
    String submitBotton = "button[type='submit']";
    static String url = config.getUrlPrivat();


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
        step("Открытие сесии", () -> {
            $(submitBotton).shouldHave(disabled).click();
            $(name).val("tester@cism-ms.ru");
            $(password).val("id7@cS2jA");
            $(submitBotton).shouldNotHave(disabled).click();
            $("title").shouldHave(attribute("text", "oko"));
        });
    }

    @AfterEach
    public void cleanSession() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        if (config.videoStorage() != null)
            attachVideo();
        step("Закрытие сесии", () -> {
            $(".logo").click();
            $(".user-info").click();
            $(byText("Выйти")).click();
        });
        closeWebDriver();

    }


}
