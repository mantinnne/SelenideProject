package Methud.Oko;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.containTexts;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SchoolSteps {

    SelenideElement sortSchoolboyField = $(".vs__actions", 0),
            filterSchoolboyField = $(".vs__actions", 1);

    ElementsCollection xx = $$(".vs__dropdown-option");

    @Step("Выбор раздела школы в регионе")
    public SchoolSteps selectSchoolForRegion() {
        $(byLinkText("Школы")).click();
        return this;
    }

    @Step("Ввод в поле поиска школа названия школы")
    public SchoolSteps inputNameSchoolIsSearch() {
        $x("//input[@class='app-input plain size-lg']").val("Школа Свиблово");
        return this;
    }

    @Step("Открытие округа отображения школы")
    public SchoolSteps openDictrictVievSchool() {
        $$(".group-panel").first().click();
        return this;
    }

    @Step("Проверка отображения результатов поиска школы")
    public SchoolSteps checkResultSearchSchools() {
        $$(".school-name").shouldHave(texts("Школа Свиблово")).shouldHaveSize(1);
        return this;
    }

    @Step("Проверка отображения школ в округе")
    public SchoolSteps checkVievSchoolForDictrict() {
        $(".group-list").shouldBe(visible, Duration.ofSeconds(30));
        return this;
    }

    @Step("Нахождение списка школ и выбор первой школы из списка")
    public SchoolSteps findFirstSchoolOnList() {
        $$(".school").first().click();
        return this;
    }

    @Step("Переключение на окно школы ")
    public SchoolSteps switchWindows(int index) {
        switchTo().window(index);
        return this;
    }

    @Step("Проверка загрузки данных по школе")
    public SchoolSteps checkLoadingSchools() {
        $(".school-header").shouldBe(visible);
        $(".school-children-table table").shouldBe(visible);
        $(".school-sidebar-about").shouldBe(visible);
        return this;
    }

    @Step("Проверка неактивности перехода на на начальсную и прошлую страницу выборки школьников при выборе первоначальной страницы")
    public SchoolSteps checkDisableNavigationForListSchoolboy() {
        $$(".pagination > li.disabled").shouldHaveSize(2);
        return this;
    }

    @Step("Выборка второй страницы школьников")
    public SchoolSteps selectSchoolboyPageTwo() {
        $$(".pagination >li").find(text("2")).click();
        return this;
    }

    @Step("Проверка загрузки второй страницы школьников")
    public SchoolSteps checkTwoPageSchoolboy() {
        $("table.table").shouldBe(visible);
        return this;
    }

    @Step("Открытие статистики по школе")
    public SchoolSteps openStaticticForSchool() {
        $(byText("Статистика")).click();
        return this;
    }

    @Step("Проверка появления статистики по школе")
    public SchoolSteps checkOpenStaticticsSchool() {
        $("article.body").shouldBe(visible);
        $(".school-sidebar-statistic").shouldBe(visible);
        return this;
    }

    @Step("Сортировка школьников")
    public SchoolSteps sortSchoolboy() {
        sortSchoolboyField.click();
        for (int i = 0; i < xx.size(); i++) {
            String text = $(xx.get(i)).getText();
            $(xx.get(i)).click();
            $(".school-children-table table").shouldBe(visible);
            assertThat(text, is($("span.vs__selected").getText()));
            sortSchoolboyField.click();
        }
        return this;
    }

    @Step("Фильтрация  школьников по направлениям")
    public SchoolSteps filterSchoolboy() {
        filterSchoolboyField.click();
        for (int i = 0; i < xx.size(); i++) {
            String text = $(xx.get(i)).getText();
            $(xx.get(i)).click();
            if (i == 4) return this;
            $(".school-children-table table").shouldBe(visible);
            $$(".directions-cell > span").shouldHave(containTexts(text));
            filterSchoolboyField.click();
        }
        return this;
    }
}
