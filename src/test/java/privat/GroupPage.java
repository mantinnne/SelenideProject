package privat;

import Methud.privat.GroupSteps;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.BasePrivat;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class GroupPage extends BasePrivat {

    GroupSteps steps = new GroupSteps();

    final static String group = "div.table",
            groupList = "//tbody/tr",
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
    void checkLoadingGroupPage() {
        steps.selectGroup();

    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка загрузки допольнительных профилей")
    void loadingMoreGroup() {
        steps.selectGroup();
        steps.checkLoadingGroup(100);
        steps.checkListGroup(100);
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка сортировки групп")
    void sortGroup() {
        steps.selectGroup();
        steps.sortGroupInPage();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Поиск групп")
    void searchGroup() {
        steps.selectGroup();
        steps.inputSeachNameGroup("Selo Company");
        steps.applyRelult();
        steps.checkVievGroup("Selo Company");

    }

    @Test
    @Story("Privat")
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Low")})
    @DisplayName("Загрузка допольнительных фильтров у группы")
    void loadingMoreFilter() {
        steps.selectGroup();
        steps.loadingMoreFilterGroup();
        steps.checkLoadingMoreFilterGroup();
    }

    @Test
    @Disabled("Не работает из-за невозможности применить фильтр, без указания имени")
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Поиск групп  ID")
    void searchGroupFilterId() {
        steps.selectGroup();
        steps.loadingMoreFilterGroup();
        steps.inputIdGroupSeach("120306116");
        steps.applyRelult();
        steps.checkVievGroup("Selo Company");
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Поиск групп по названию")
    void searchGroupFilterName() {
        steps.selectGroup();
        steps.loadingMoreFilterGroup();
        steps.inputNameGroupFilter("BlackRose-DeadRose");
        steps.applyRelult();
        steps.checkVievGroup("BlackRose-DeadRose");
    }

    @Test
    @Disabled("Не работает из-за невозможности применить фильтр, без указания имени")
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Поиск групп по названию домена")
    void searchGroupFilterDomen() {
        steps.selectGroup();
        steps.loadingMoreFilterGroup();
        steps.inputDomenGroupFilter("forest_offnik");
        steps.applyRelult();
        steps.checkVievGroup("forest_offnik");
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
