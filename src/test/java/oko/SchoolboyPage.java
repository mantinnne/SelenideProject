package oko;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.BaseOko;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SchoolboyPage extends BaseOko {
    SchoolPage schoolPage = new SchoolPage();
    ElementsCollection schoolLis = $$(".app-inline-link > a"),
            tab = $$(".tab"),
            dissierSchoolBoyList = $$(".link-container >a");

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Medium")})
    @DisplayName("Выбор личного профиля  школьника")
    void schoolboySelectDossier() {
        step("Выбор школы", () -> schoolPage.regionSelectSchool());
        step("Просмотр школьников и выбор первого школьника", () -> schoolLis.first().click());
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
                                $(".profile-photos").shouldBe(visible);
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
                                $(".activity__controls-wrapper").shouldBe(visible);
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
    @DisplayName("Проверка выбора методики у профиля во вкладке сетевая активность")
    void profileSchoolboyCismCosialProfileFilterMetodic() {
        step("Выбор профиля школьника", this::schoolboySelectDossier);
        tab.get(1).click();
        step("Выборка методики у профиля для отображения", () -> {
            ElementsCollection collection = $$(".methodic__select-methodic > option").shouldHaveSize(3);
            for (int i = 1; i < collection.size(); i++) {
                collection.get(i).click();
                $(".methodic").shouldBe(visible);
            }
        });
    }
}
