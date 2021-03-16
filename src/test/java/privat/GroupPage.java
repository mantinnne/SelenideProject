package privat;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.BasePrivat;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class GroupPage extends BasePrivat {
    final static String group = "div.table";
    String groupList = "//tbody/tr",
            dropdownSelected = "vs__dropdown-option--selected";

    final SelenideElement loadingFilter = $(byLinkText("Больше фильтров")),
            groupIdFilter = $("input[type='number']"),
            groupNameFilter = $("#group-name"),
            groupDomenFilter = $("#screen_name"),
            groupFilterCosialNetwork = $(".vs__selected-options", 0),
            groupDirectionFilter = $(".vs__selected-options", 1),
            bounceWait = $(".double-bounce2"),
            buttonFilter = $(".app-button");
    final ElementsCollection conutGroupFilterList = $$("li.vs__dropdown-option"),
            conutDirectionFilterList = $$("li.vs__dropdown-option");



    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Открытие групп")
    void selectGroup() {
        step("Открытие раздела группы", () -> $(By.linkText("Группы")).click());
        step("Проверка отображения загрузки списка групп", () -> {
            $(group).shouldBe(visible);
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка загрузки допольнительных профилей")
    void loadingMoreGroup() {
        step("Открытие списка групп", this::selectGroup);

        step("Загрузка дополнительных профилей", () -> {
            ElementsCollection groups = $$x(groupList);

            for (int i = 0; i < 10; i++) {
                int s = groups.size();
                $(".button-load-more").click();

                step("Проверка загрузки дополнительных профилей", () -> {
                    groups.shouldHaveSize(s + 10);
                });
            }
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка сортировки групп")
    void sortGroup() {
        step("Открытие списка групп", this::selectGroup);

        step("Сортировка групп ", () -> {
            ElementsCollection sortSelect = $$("a.sort-by").shouldHaveSize(3);
            for (SelenideElement element : sortSelect) {
                element.click();
                step("Проверка загрузки отсортированных групп", () -> {
                    $(bounceWait).shouldBe(disappear);
                });
            }
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Поиск групп")
    void searchGroup() {
        step("Открытие списка групп", this::selectGroup);

        step("Ввод в поисковое окно название группы и нажатие на кнопку поиска ", () -> {
            SelenideElement searchField = $("input[type='text']").shouldBe(visible);
            searchField.val("Selo Company");
            buttonFilter.click();
        });

        step("Проверка отображение нужной группы", () -> {
            $$x(groupList).shouldHaveSize(1);
        });

    }

    @Test
    @Story("Privat")
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Low")})
    @DisplayName("Загрузка допольнительных фильтров у группы")
    void loadingMoreFilter() {
        step("Открытие списка групп", this::selectGroup);

        step("Загрузка допольнительных фильтров группы", (Allure.ThrowableRunnableVoid) loadingFilter::click);

        step("Проверка отображения дополнительных фильтров", () -> {
            groupIdFilter.shouldBe(visible);
            groupNameFilter.shouldBe(visible);
            groupDomenFilter.shouldBe(visible);
            groupFilterCosialNetwork.shouldHave(visible);
            groupDirectionFilter.shouldHave(visible);
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Поиск групп  ID")
    void searchGroupFilterId() {
        step("Открытие допольнительных фильтров у группы", this::loadingMoreFilter);

        step("Ввод значение id в поле и нажатие на кнопку поиска", () -> {
            groupIdFilter.val("1234");
            buttonFilter.click();
        });
        step("Проверка отображение нужной группы", () -> {
            //        $$x(groupList).shouldHaveSize(1);

        });


    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Поиск групп по названию")
    void searchGroupFilterName() {
        step("Открытие допольнительных фильтров у группы", this::loadingMoreFilter);
        step("Ввод названия группы в поле фильтра и нажатие на кнопку поиска", () -> {
            groupNameFilter.val("BlackRose-DeadRose");
            buttonFilter.click();
        });
        step("Проверка отображение нужной группы", () -> {
            $$x(groupList).shouldHaveSize(1);

        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Поиск групп по названию домена")
    void searchGroupFilterDomen() {
        step("Открытие допольнительных фильтров у группы", this::loadingMoreFilter);
        step("Ввод названия домена группы в поле фильтра и нажатие на кнопку поиска", () -> {
            groupDomenFilter.val("forest_offnik");
            buttonFilter.click();
        });
        step("Проверка отображение результатов фильтра", () -> {
            //        $$x(groupList).shouldHaveSize(1);
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Поиск групп по социальной сети")
    void cosialNetworkFilterGroup() {
        step("Открытие допольнительных фильтров у группы", this::loadingMoreFilter);
        step("Выборка фильтра по сициальной сети ", (Allure.ThrowableRunnableVoid) groupFilterCosialNetwork::click);
        step("Проверка количества доступных фильтров ", () -> {
            conutGroupFilterList.shouldHaveSize(5);
        });
        step("Выборка доступного фильтра для проверки отображения кнопки сброса фильтров ", () -> {
            conutGroupFilterList.get(1).click();
            groupFilterCosialNetwork.click();
            conutGroupFilterList.shouldHaveSize(6);
        });

        step("Перебор фильтрации по социальной сети и проверка загрузки групп", () -> {
            for (int i = 1; i < conutGroupFilterList.size(); i++) {
                conutGroupFilterList.get(i).click();
                groupFilterCosialNetwork.click();
                $(conutGroupFilterList.get(i)).shouldHave(cssClass(dropdownSelected));
                bounceWait.shouldBe(disappear);
            }
        });
        step("Сброс фильтров ", () -> {
            groupFilterCosialNetwork.click();
            conutGroupFilterList.get(0).click();
            bounceWait.shouldBe(disappear);
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Поиск групп по направлениям")
    void cosialDirectionFilterGroup() {
        step("Открытие допольнительных фильтров у группы", this::loadingMoreFilter);
        step("Выборка фильтра по направлениям ", (Allure.ThrowableRunnableVoid) groupDirectionFilter::click);
        step("Выборка доступного фильтра для проверки отображения кнопки сброса фильтров ", () -> {
            conutDirectionFilterList.shouldHaveSize(45);
            conutDirectionFilterList.get(1).click();
            groupDirectionFilter.click();
            conutDirectionFilterList.shouldHaveSize(46);
        });
        step("Перебор фильтрации по направлениям и проверки загрузки групп", () -> {
            for (int i = 1; i < conutDirectionFilterList.size(); i++) {
                conutDirectionFilterList.get(i).shouldNotHave(disabled).click();
                groupDirectionFilter.click();
                $(conutDirectionFilterList.get(i)).shouldHave(cssClass(dropdownSelected));
                bounceWait.shouldBe(disappear);
            }
        });
        step("Сброс фильтров ", () -> {
            groupDirectionFilter.click();
            conutDirectionFilterList.get(0).click();
        });


    }
}
