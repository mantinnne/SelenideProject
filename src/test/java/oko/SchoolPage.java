package oko;

import com.codeborne.selenide.ElementsCollection;
import config.BaseOko;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SchoolPage extends BaseOko {
    RegionPage regionPage = new RegionPage();


    final static String school = "Школы";
    final String actions = ".vs__actions";

    @Test
    @Story("Oko")

    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Medium")})
    @DisplayName("Поиск школы")
    void seacrhSchool() {
        step("Выборка региона", () -> regionPage.selectRegionView("Москва"));
        step("Выборка раздела школы", () -> $(byLinkText(school)).click());
        step("Ввод в поле поиска школа названия школы", () -> $x("//input[@class='app-input plain size-lg']").val("Школа Свиблово"));
        step("Открытие округа отображения школы", () -> $(".group-panel").click());
        step("Проверка отображения результатов поиска школы", () -> $(".school-name").shouldBe(text("Школа Свиблово")));
        step("Проверка отображения количества школ по результатам поиска", () -> $$(".school").shouldHaveSize(1));

    }

    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Medium")})
    @DisplayName("Выбор школы")
    void regionSelectSchool() {
        step("Выборка региона", () -> regionPage.selectRegionView("Москва"));
        step("Выборка раздела школы", () -> $(byLinkText(school)).click());
        step("Открытие округа для отображения школы", () -> $("span.title").click());
        step("Проверка отображения школ в округе", () -> $(".group-list").shouldBe(visible, Duration.ofSeconds(30)));
        step("Нахождение списка школ и выбор первой школы из списка", () -> $$(".school").first().click());

        step("Переключение на окно школы и проверка загрузки данных по школе", () -> {
            switchTo().window(1);
            $(".school-header").shouldBe(visible);
            $(".school-children-table table").shouldBe(visible);
            $(".school-sidebar-about").shouldBe(visible);
        });
    }


    @Test
    @Story("Oko")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Low")})
    @DisplayName("Проверка выборки страницы школьников")
    void regionSelectPageSchool() {
        step("Выбор школы", this::regionSelectSchool);
        step("Проверка неактивности перехода на на начальсную и прошлую страницу выборки школьников", () -> $$(".disabled").shouldHaveSize(2));
        step("Выборка второй страницы школьников", () -> $(byText("2")).click());
        step("Проверка загрузки второй страницы школьников", () -> $("table.table").shouldBe(visible));

    }


    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("High")})
    @DisplayName("Загрузки статистики по школе")
    void regionSelectStaticticSchool() {
        step("Выбор школы", this::regionSelectSchool);
        step("Открытие статистики по школе", () -> $(byText("Статистика")).click());
        step("Проверка появления статистики по школе", () -> {
            $("article.body").shouldBe(visible);
            $(".school-sidebar-statistic").shouldBe(visible);
        });
    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Medium")})
    @DisplayName("Сортировка школьников")
    void regionSelectSortSchoolboy() {
        step("Выбор школы", this::regionSelectSchool);
        step("Открытие окна сортировки", () -> $(actions, 0).click());
        step("Перебор значений сортировки", () -> {
            ElementsCollection xx = $$(".vs__dropdown-option");
            for (int i = 0; i < xx.size(); i++) {
                $(xx.get(i)).click();
                step("Проверка отображения школьников при применении результатов", () -> {
                    $(".school-children-table table").shouldBe(visible);
                    $(actions, 0).click();
                });
            }
        });
    }

    @Test
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("Medium")})
    @DisplayName("Фильтрация школьников по направлениям")
    void regionSelectFilterSchoolboy() {
        step("Выбор школы", this::regionSelectSchool);
        step("Открытие окна фильтрации", () -> $(actions, 1).click());
        step("Перебор значений сортировки", () -> {

            ElementsCollection filterSchoolboy = $$(".vs__dropdown-option");
            for (int i = 0; i < filterSchoolboy.size(); i++) {
                $(filterSchoolboy.get(i)).click();
                step("Проверка отображения школьников при применении результатов фильтрации", () -> {
                    $(".school-sidebar").shouldBe(visible);
                    $(".school-children-table").shouldBe(visible);
                    $(actions, 1).click();
                });
            }
        });
    }
}

