package Methud.privat;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupSteps {

    final SelenideElement loadingFilter = $(byLinkText("Больше фильтров")),
            groupIdFilter = $("input[type='number']"),
            groupNameFilter = $("#group-name"),
            groupDomenFilter = $("#screen_name"),
            group = $("div.table"),
            groupFilterCosialNetwork = $(".vs__selected-options", 0),
            groupDirectionFilter = $(".vs__selected-options", 1),
            bounceWait = $(".double-bounce2"),
            searchField = $(".group-search__input"),
            buttonFilter = $(".app-button");


    final ElementsCollection conutGroupFilterList = $$("li.vs__dropdown-option"),
            conutDirectionFilterList = $$("li.vs__dropdown-option"),
            groups = $$x("//tbody/tr"),
            sortSelect = $$("a.sort-by");


    @Step("Открытие раздела Группы")
    public GroupSteps selectGroup() {
        $(By.linkText("Группы")).click();
        group.shouldBe(visible);

        return this;
    }

    @Step("Загрузка дополнительных профилей")
    public GroupSteps checkLoadingGroup(int number) {
        for (int i = groups.size(); i < number; i = groups.size()) {
            $(".button-load-more").click();
        }
        return this;
    }

    @Step("Проврка отображения количества групп на странице")
    public GroupSteps checkListGroup(int number) {
        groups.shouldHaveSize(number);
        return this;
    }

    @Step("Сортировка групп")
    public GroupSteps sortGroupInPage() {
        sortSelect.shouldHaveSize(3);
        for (SelenideElement element : sortSelect) {
            element.click();
            bounceWait.shouldBe(disappear);
        }
        return this;
    }

    @Step("Ввод в поисковое окно название группы: {0} и нажатие на кнопку поиска")
    public GroupSteps inputSeachNameGroup(String name) {
        searchField.val(name);
        return this;
    }

    @Step("Проверка  отображения  {0} группы")
    public GroupSteps checkVievGroup(String name) {
        assertThat(groups.shouldHaveSize(1).get(0).getText(), is(containsString(name)));
        return this;
    }

    @Step("Загрузка дополнительны фильтров")
    public GroupSteps loadingMoreFilterGroup() {
        $(byLinkText("Больше фильтров")).click();
        return this;
    }

    @Step("Ввод значения id: {0} в поле фильтрации группы по ID")
    public GroupSteps inputIdGroupSeach(String id) {
        groupIdFilter.val(id);
        return this;
    }

    @Step("Применение результатов поиска")
    public GroupSteps applyRelult() {
        buttonFilter.click();
        return this;
    }

    @Step("Ввод названия группы: {0}  в поле фильтра")
    public GroupSteps inputNameGroupFilter(String name) {
        groupNameFilter.val(name);
        return this;
    }

    @Step("Ввод домены группы: {0}  в поле фильтра")
    public GroupSteps inputDomenGroupFilter(String name) {
        groupDomenFilter.val(name);
        return this;
    }

    @Step("Провезка отображения допольнительных фильтров")
    public GroupSteps checkLoadingMoreFilterGroup() {
        groupIdFilter.shouldBe(visible);
        groupNameFilter.shouldBe(visible);
        groupDomenFilter.shouldBe(visible);
        groupFilterCosialNetwork.shouldHave(visible);
        groupDirectionFilter.shouldHave(visible);

        return this;
    }
}

