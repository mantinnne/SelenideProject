package oko;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.BaseOko;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SchoolboyPage extends BaseOko {
    SchoolPage schoolPage = new SchoolPage();
    ElementsCollection schoolLis = $$x("//table[@class='table']//tr"),
            tab = $$(".tab"),
            dissierSchoolBoyList = $$(".link-container >a");

    SelenideElement profileBody = $(".profile-body");

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Medium")})
    @DisplayName("Выбор досье школьника")
    void schoolboySelectDossier() {
        step("Выбор школы", () -> schoolPage.regionSelectSchool());
        step("Просмотр школьников и выбор первого школьника", () -> schoolLis.first().click());
        step("Переход в досье школьника", () -> {
            $(byText("Досье")).click();
            switchTo().window(3);
        });
        step("Проверка загрузки  данных по школьника", () -> {
            $(".dossier__general-data").shouldBe(visible);
            $(".dossier__content").shouldBe(visible);
            $(".dossier__dynamic").shouldBe(visible);
        });
    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Medium")})
    @DisplayName("Просмотр данных доступных по школьнику")
    void schoolboyCheckInfo() {
        step("Выбор досье школьника", this::schoolboySelectDossier);
        step("Выбор раздела с личными данными", () -> {
            tab.shouldHaveSize(2);
            tab.get(1).click();
            $(".link-container").shouldBe(visible);
            step("Проход по разделам досье школьника", () -> {
                dissierSchoolBoyList.shouldHaveSize(6);
                dissierSchoolBoyList.get(3).click();
                for (SelenideElement element : dissierSchoolBoyList) {
                    element.click();
                    element.shouldHave(cssClass("nuxt-link-active"));
                    step("Проверка загрузки данных по досье у школьника", () -> {
                        String textFildDossier = element.getText();
                        switch (textFildDossier) {
                            case "Сетевой профиль":
                                $(".profile-text-data__header").shouldBe(visible);
                                $(".profile-text-data__info").shouldBe(visible);
                                $(".methodic").shouldBe(visible);
                                $(".profileStats").shouldBe(visible);
                                break;
                            case "Фото профиля":
                                $(".dossier-profile-photos").shouldBe(visible);
                                break;
                            case "Статусы профиля":
                                $(".profile-statuses__data").shouldBe(visible);
                                break;
                            case "Связи/друзья профиля":
                                $(".friends-list").shouldBe(visible);
                                break;
                            case "Группы профиля":
                                $(".groups-sidebar").shouldBe(visible);
                                break;
                            case "Сетевая активность":
                                $(".profile__activity").shouldBe(visible);
                                break;
                        }
                    });
                }
            });

        });
    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Medium")})
    @DisplayName("Выбор профиля школьника")
    void schoolboySelectProfileCISM() {
        step("Выбор школы", () -> schoolPage.regionSelectSchool());
        step("Просмотр школьников и выбор первого школьника", () -> schoolLis.first().click());
        step("Выбор профиля школьника", () -> $(byText("Профиль ЦИСМ")).click());
        step("Проверка загрузки профиля школы", () -> {
            switchTo().window(3);
            $(".profile-general-data").shouldBe(visible);
        });
    }


    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Medium")})
    @DisplayName("Проверка разделов навигации по профилю школьника")
    void profileSchoolboyCismNavigation() {
        step("Выбор профиля школьника", this::schoolboySelectProfileCISM);
        step("Перебор навигации разделов профиля школьника", () -> {
            ElementsCollection tabs = $$("li.tab").shouldHaveSize(7);
            for (int i = 0; i < tabs.size(); i++) {
                tabs.get(i).click();
                String tabText = tabs.get(i).getText();
                step("Переход по разделам профиля школьника и проверка загрузки данных", () -> {
                    switch (tabText) {
                        case "Общие данны":
                            $(".profile-general-data").shouldBe(visible);
                            break;
                        case "Сетевой профиль":
                            $("section.profile-network-profile").shouldBe(visible);
                            break;
                        case "Фото":
                            profileBody.shouldBe(visible);
                            break;
                        case "Статусы":
                            profileBody.shouldBe(visible);
                            break;
                        case "Связи / друзья":
                            profileBody.shouldBe(visible);
                            break;
                        case "Группы":
                            profileBody.shouldBe(visible);
                            break;
                        case "Сетевая активность":
                            profileBody.shouldBe(visible);
                            break;

                    }
                });
            }
        });
    }

}
