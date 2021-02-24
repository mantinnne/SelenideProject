package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public abstract class BasePrivat {

    public Faker faker = new Faker();
    String name = "input[type='email']";
    String password = "input[type='password']";
    String submitBotton = "button[type='submit']";
    String url = "http://oko-private-stage.cism-ms.ru/";
    String profileList = ".profiles-list";


    @BeforeAll

    public static void setUp() {

        setSelenideConfiguration();
        SelenideLogger.addListener("allure", new AllureSelenide());

    }


    @BeforeEach
    void login() {

        step("Открытие сайта " + url, () -> open(url));
        step("Открытие сесии", () -> {
            $(submitBotton).shouldHave(disabled).click();
            $(name).val("tester@cism-ms.ru");
            $(password).val("id7@cS2jA");
            $(submitBotton).shouldNotHave(disabled).click();
        });
        step("Проверка загрузки главной страницы", () -> {
            $("title").shouldHave(attribute("text", "oko"));
            $(profileList).shouldBe(visible, Duration.ofSeconds(15));
        });


    }

    @AfterEach
    public void cleanSession() {

        step("Закрытие сесии", () -> {
            $(".logo").click();
            $(".user-info").click();
            $(byText("Выйти")).click();
        });
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.close();
    }

    private static void setSelenideConfiguration() {
        Configuration.timeout = 10000;
        Configuration.startMaximized = true;

    }

}
