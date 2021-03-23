package Methud.Oko;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class SchoolboySteps {
    ElementsCollection schoolLis = $$(".app-inline-link > a"),
            tab = $$(".tab"),
            dissierSchoolBoyList = $$(".link-container >a");


    @Step("Просмотр школьников и выбор первого школьника")

    public SchoolboySteps selectFirstSchoolboy() {
        schoolLis.first().click();
        return this;
    }

    @Step("Проход по разделам досье школьника")
    public SchoolboySteps openInfoSchoolboy() {
        tab.shouldHaveSize(2);
        tab.get(1).click();
        $(".link-container").shouldBe(visible);
        dissierSchoolBoyList.shouldHaveSize(6);
        dissierSchoolBoyList.get(3).click();
        for (SelenideElement element : dissierSchoolBoyList) {
            element.click();
            element.shouldHave(cssClass("nuxt-link-active"));
            String textFildDossier = element.getText();
            switch (textFildDossier) {
                case "Сетевой профиль":
                    $(".profile-text-data__header").shouldBe(visible);
                    $(".profile-text-data__info").shouldBe(visible);
                    $(".methodic").shouldBe(visible);
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
        }
        return this;
    }
}

