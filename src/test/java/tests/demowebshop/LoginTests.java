package tests.demowebshop;

import com.codeborne.selenide.Configuration;
import config.projetct.Appilication;
import helpers.AllureRestAssuredFilter;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import tests.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

@Story("Login tests")

public class LoginTests extends TestBase {
    @BeforeAll
    static void configureBaseUrl() {
        RestAssured.baseURI = Appilication.config.apiUrl();
        Configuration.baseUrl = Appilication.config.webUrl();
    }

    @Test
    @Tag("demowebshop")
    @DisplayName("Successful authorization to some demowebshop (UI)")
    void loginTest() {
        step("Open login page", () ->
                open("/login"));

        step("Fill login form", () -> {
            $("#Email").setValue(Appilication.config.userLogin());
            $("#Password").setValue(Appilication.config.userPassword())
                    .pressEnter();
        });

        step("Verify successful authorization", () ->
                $(".account").shouldHave(text(Appilication.config.userLogin())));
    }

    @Test
    @Tag("demowebshop")
    @DisplayName("Successful authorization to some demowebshop (API + UI)")
    void loginWithCookieTest() {
        step("Get cookie by api and set it to browser", () -> {
            String authorizationCookie =
                    given()
                            .filter(AllureRestAssuredFilter.withCustomTemplates())
                            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                            .formParam("Email", Appilication.config.userLogin())
                            .formParam("Password", Appilication.config.userPassword())
                            .when()
                            .post("/login")
                            .then()
                            .statusCode(302)
                            .extract()
                            .cookie("NOPCOMMERCE.AUTH");

            step("Open minimal content, because cookie can be set when site is opened", () ->
                    open("/Themes/DefaultClean/Content/images/logo.png"));

            step("Set cookie to to browser", () ->
                    getWebDriver().manage().addCookie(
                            new Cookie("NOPCOMMERCE.AUTH", authorizationCookie)));
        });

        step("Open main page", () ->
                open(""));

        step("Verify successful authorization", () ->
                $(".account").shouldHave(text(Appilication.config.userLogin())));
    }
}