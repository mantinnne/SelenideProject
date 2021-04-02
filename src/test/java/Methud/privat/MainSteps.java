package Methud.privat;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MainSteps {
    Faker faker = new Faker();


    String dropdownSelected = "vs__dropdown-option--selected";


    final ElementsCollection
            profileListViev = $$(".profile-info-container"),
            trafficValues = $$(".values-count"),
            filterChechBox = $$("label.checkbox-label"),
            conutGroupFilterList = $$x("//ul[@id='vs5__listbox']//li"),
            directionList = $$x("//ul[@id='vs2__listbox']//li");


    final SelenideElement profilesAggRatings = $(".profiles-agg-ratings"),
            region = $("div#vs1__combobox"),
            idFilter = $(".filter-input"),
            direction = $("div#vs2__combobox"),
            conutGroupFilter = $("div#vs5__combobox"),
            countGroup = $(conutGroupFilter),
            cosialNetwork = $("div#vs3__combobox").as("Выбор поля для фильтрации по социальной сети"),
            metodic = $("div#vs4__combobox").as("Выбор поля для фильтрации по методике"),
            textArea = $("textarea"),
            buttonSave = $(withText("Сохранить")),
            button = $(".app-button");


    @Step("Нажатие на кнопку загрузки дополнительных профилей")
    public MainSteps clickButtonLoading() {
        $(".button-load-more").click();
        return this;
    }

    @Step("Проверка отображения количества профилей на странице")
    public MainSteps checkLoadingProfile() {
        profileListViev.shouldHaveSize(20);
        return this;
    }

    @Step("Проверка отображение загрузки светофора профилей")
    public MainSteps loadingTrafficLight() {
        profilesAggRatings.shouldBe(visible, Duration.ofSeconds(15));
        return this;
    }

    @Step("Проверка значения у светофоров")
    public MainSteps checkTrafficValue() {
        for (SelenideElement element : trafficValues) {
            String text = element.getText();
            assertNotEquals(null, text);
        }
        return this;
    }

    @Step("Проверка доступности выборки фильтров забаненные/удаленные")
    public MainSteps checkFilterDeletedOrBan() {
        for (SelenideElement chechBox : filterChechBox) {
            chechBox.click();
        }
        return this;
    }

    @Step("Проверка появления возможности  сбросить фильтр")
    public MainSteps checkResetFilter(SelenideElement element, ElementsCollection elements, int size, int fullSize) {
        element.click();
        elements.shouldHaveSize(size).get(1).click();
        element.click();
        elements.shouldHaveSize(fullSize);
        return this;
    }

    @Step("Перебор значений фильтрации по региону")
    public MainSteps selectAllFilter(SelenideElement element, ElementsCollection elements) {
        for (int i = 1; i < elements.size(); i++) {
            elements.get(i).click();
            element.click();
            elements.get(i).shouldHave(cssClass(dropdownSelected));
        }
        return this;
    }

    @Step("Выбор фильтрации профилей напарвлениям ")
    public MainSteps selectDirection() {
        for (int i = 1; i < directionList.size(); i++) {
            directionList.get(i).click();
            direction.click();
            directionList.get(i).shouldHave(cssClass(dropdownSelected));
            checkResetFilter(countGroup, conutGroupFilterList, 4, 5);
            selectCountGroupForDirection();
            direction.click();
        }
        return this;
    }


    @Step("Выбор количества групп у профиля по выбранному направлению")
    public MainSteps selectCountGroupForDirection() {
        for (int i = 1; i < conutGroupFilterList.size(); i++) {
            profilesAggRatings.shouldBe(visible, Duration.ofSeconds(15));
            for (SelenideElement element : trafficValues) {
                String text = element.getText();
                if (text.equals("0")) {
                    continue;
                }
                conutGroupFilterList.get(i).click();
                countGroup.click();
                conutGroupFilterList.get(i).shouldHave(cssClass(dropdownSelected));
            }
        }
        countGroup.click();
        conutGroupFilterList.get(0).click();
        return this;
    }

    @Step("Скрытие фильтров для отображение для профилей")
    public MainSteps hiddenFilter() {
        $(byLinkText("Меньше фильтров")).click();
        return this;
    }

    @Step("Проверка, что были скрыты поля для фильтрации")
    public MainSteps checkHiddenFilter() {
        region.shouldHave(disappear);
        direction.shouldHave(disappear);
        conutGroupFilter.shouldHave(disappear);
        metodic.shouldHave(disappear);
        idFilter.shouldHave(disappear);
        conutGroupFilter.shouldHave(disappear);

        return this;
    }

    @Step("Выбор быстрой фильтрации профилей")
    public MainSteps selectFastSeachProfile() {
        $(byLinkText("Быстрая проверка")).click();
        $(".profiles-check").shouldBe(visible);
        return this;
    }

    @Step("Геренация рандомных id профилей для проверки поиска")
    public MainSteps generationIdProfile() {
        int counter = faker.random().nextInt(4, 6);
        for (int i = 0; i <= counter; i++) {
            Integer numberID = faker.random().nextInt(1, 10000);
            textArea.append(numberID + "\n");
        }
        return this;
    }


    @Step("Ввод в поле сортировки профилей id: > {0} ")
    public MainSteps inputFirstIdProfile(String id) {
        idFilter.val(id);
        return this;
    }

    @Step("Ввод  в поиск профилей нескольких id")
    public MainSteps SearcSomeProfileResult(int counter) {
        for (int i = 1; i <= counter; i++) {
            Integer numberID = faker.random().nextInt(1, 10000);
            if (i < counter) {
                idFilter.append(numberID + ",");
            } else {
                idFilter.append(String.valueOf(numberID));
            }
        }
        return this;
    }

    @Step("Выбор фильтра")
    public MainSteps selectFilter(SelenideElement element) {
        element.click();
        return this;
    }

    @Step("Выбор фильтрации профилей по методике и рейтингу")
    public MainSteps selectMetodicFilter() {
        metodic.click();
        return this;
    }


    @Step("Проверка отображения результатов поиска по id пользователя")
    public MainSteps checkSearchProfileRelult(int profileSize, String id) {
        profileListViev.shouldHaveSize(profileSize);
        String text = $(".name-link > a").getText();
        assertThat(text).contains(id);
        return this;
    }


    @Step("Применение результатов поиска  профилей")
    public MainSteps applyResult() {
        button.click();
        return this;
    }

    @Step("Ожидание загрузки найденных профилей")
    public MainSteps waitResultSearchProfile() {
        $(".list").shouldBe(visible);
        $(".double-bounce2").shouldBe(disappear, Duration.ofSeconds(40));
        return this;
    }

    @Step("Проверка возможности добавить пользовател, которого нет в базе данных")
    public MainSteps checkAddProfileIsNotBase() {
        $(byText("Не добавлять")).click();
        buttonSave.click();
        buttonSave.shouldBe(disappear);


        return this;
    }


    @Step("Сброс фильтра по поиску id")
    public MainSteps resetFilterId() {
        idFilter.clear();
        button.click();
        return this;
    }


    @Step("Сброс фильтра по выборке с выпадающим списком")
    public MainSteps resetFilterSelected(SelenideElement element, ElementsCollection elements) {
        element.click();
        elements.get(0).click();
        return this;
    }
}
