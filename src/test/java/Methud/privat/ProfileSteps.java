package Methud.privat;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class ProfileSteps {

    Faker faker = new Faker();
    final SelenideElement
            profileData = $(".profile-main-data"),
            groupData = $("div.profile-component-container"),
            friendsData = $(".friends-list"),
            retroData = $(".profile-retro__data"),
            analyticsData = $(".profile-analytics"),
            monitoringData = $(".profile-anomalies"),
            activityData = $(".profile__activity"),
            createReportFild = $(".toasted-primary"),
            bounceWait = $(".double-bounce2"),
            profileGroupBody = $(".report-publics__container"),
            profileFriendsBody = $(".profile-component"),
            selectGroupInPrifle = $(".link-container").$(byText("Группы")),
            selectFriendsInPrifle = $(".link-container").$(byText("Друзья")),
            selectRetroInPrifle = $(".link-container").$(byText("Ретроспектива")),
            selectAnalyticsInPrifle = $(".link-container").$(byText("Аналитика")),
            selectEventInPrifle = $(byText("События")),
            selectActivityInPrifle = $(byText("Активность")),
            inputBSTM = $("#bstm"),
            buttonSave = $(".app-button"),
            commentAnalytics = $(".analytics__comment"),
            periodEventProfile = $("select.custom-input"),
            tableFromProfileActivity = $(".post-table"),
            profileHeader = $(".profile-text-data__header");


    final ElementsCollection linkProfile = $$(".name-link > span"),
            prifileNavigationList = $$x("//div[@class='link-container']/a"),
            buttonCreateReport = $$(".app-button"),
            direction = $$(".vs__dropdown-option"),
            actualGroupInProfile = $$(byText("Только актуальные")),
            dateByEvent = $$("input[type='date']"),
            analyticsProfileSelectDate = $$(".analytics__select > option"),
            collection = $$(".metodic__select-metodic > option"),
            countMetodicRaiting = $$x("//table[@class='metodic__metrics']//tr"),
            lisLinkVkProfile = $$(".name-link > a "),
            analyticsProfileSelect = $$(".analytics__select"),
            analyticsProfileSelectPeriod = $$(".custom-input > option");

    @Step("Выбор личной страницы профиля: {0}")
    public ProfileSteps selectProfilePage(String name) {
        linkProfile.shouldBe(CollectionCondition.allMatch("Кликабельны все элементы коллекции для перехода в личную страницу", WebElement::isEnabled))
                .shouldHaveSize(10).filter(text(name)).first().click();
        return this;
    }

    @Step("Проверка открытого профиля: {0} и данных по нему")
    public ProfileSteps checkSelectProfilePage(String name) {
        profileData.shouldBe(visible);
        $(".profile-text-data__info").shouldBe(visible);
        profileHeader.as("Проверка, что открылся нужный профиль с тайтлом  " + name);
        assertThat(profileHeader.getText(), is(containsString(name)));
        return this;
    }

    @Step("Выбор определенного раздела у профиля")
    public ProfileSteps selectDefinitelyProfile() {
        prifileNavigationList.get(2).click();
        return this;
    }

    @Step("Навигация по всем разделам профиля")
    public ProfileSteps navigationSectionProfile() {
        for (SelenideElement element : prifileNavigationList) {
            element.as("Выбор раздела: " + element.getText()).click();
            step("Проверка, что у выбранного раздела есть класс nuxt-link-active", () -> {
                element.shouldHave(cssClass("nuxt-link-active"));
            });
            step("Проверка загрузки раздела профиля: " + element.getText(), () -> {
                switch (element.getText()) {
                    case ("Профиль"):
                        profileData.shouldBe(visible);
                        break;
                    case ("Группы"):
                        groupData.shouldBe(visible);
                        break;
                    case ("Друзья"):
                        friendsData.shouldBe(visible);
                        break;
                    case ("Ретроспектива"):
                        retroData.shouldBe(visible);
                        break;
                    case ("Аналитика"):
                        analyticsData.shouldBe(visible);
                        break;
                    case ("События"):
                        monitoringData.shouldBe(visible);
                        break;
                    case ("Активность"):
                        activityData.shouldBe(visible);
                        break;
                }
            });
        }
        return this;
    }

    @Step("Проверка неактивности сброса методики")
    public ProfileSteps checkInactiveResetMetodic() {
        refresh();
        collection.first().shouldBe(disabled);
        return this;
    }

    @Step("Выбор методики у пользователя")
    public ProfileSteps selectMetodicIsProfile() {
        for (int i = 1; i < collection.size(); i++) {
            collection.get(i).click();
            String text = collection.get(i).getText();
            switch (text) {
                case ("Деструктивная полная методика"):
                    countMetodicRaiting.shouldHaveSize(18);
                    break;
                case ("Оппозиционная полная методика"):
                    countMetodicRaiting.shouldHaveSize(14);
                    break;
            }
            bounceWait.shouldBe(disappear);
        }
        return this;
    }

    @Step("Запрос на создание  отчетов по профилю")
    public ProfileSteps downloadReportProfile() {
        buttonCreateReport.shouldHaveSize(6);
        for (SelenideElement element : buttonCreateReport) {
            element.as("Скачивание репорта по профилю: " + element.getText()).click();
            step("Ожидание появляения окна отчета и его скрытие", () -> {
                createReportFild.shouldBe(visible);
                createReportFild.shouldBe(disappear);
            });
        }
        return this;
    }

    @Step("Открытие ссылки на профиль vk у профиля")
    public ProfileSteps openLinkProfileVK() {
        lisLinkVkProfile.first().click();
        return this;
    }

    @Step("Переключение на новое окно")
    public ProfileSteps switchWindows(int number) {
        switchTo().window(number);
        return this;
    }

    @Step("Проверка открытия профиля в VK")
    public ProfileSteps checkOpenProfileVk() {
        $("title").shouldHave(attribute("text", "Elena Makarova | VK"));
        closeWindow();
        return this;
    }

    @Step("Открытие групп у  выбранного пользователя")
    public ProfileSteps selectGroupInProfile() {
        selectGroupInPrifle.click();
        return this;
    }

    @Step("Проверка загрузки групп у пользователя")
    public ProfileSteps checkLoadingGroupProfile() {
        profileGroupBody.shouldBe(visible);
        return this;
    }

    @Step("Проверка количества направлений деструктива  для фильтрации")
    public ProfileSteps checkCountFilterDirection(int size) {
        direction.shouldHaveSize(size);

        return this;
    }

    @Step("Проверка появления возможности  сбросить фильтр")
    public ProfileSteps checkResetFilter(ElementsCollection elements, int size, int fullSize, int number) {
        SelenideElement element = $(".vs__selected-options", number);
        element.click();
        elements.shouldHaveSize(size).get(1).click();
        element.click();
        elements.shouldHaveSize(fullSize);
        return this;
    }

    @Step("Выбор всех фильтров  для сортировки групп у профиля")
    public ProfileSteps selectAllDirectionGroupFilter(ElementsCollection elements, int number) {
        SelenideElement element = $(".vs__selected-options", number);

        for (int i = 1; i < direction.size(); i++) {
            elements.get(i).click();
            bounceWait.shouldBe(hidden);
            element.click();
            elements.get(i).shouldHave(cssClass("vs__dropdown-option--selected"));
        }
        return this;
    }

    @Step("Нахождение выборки актуальных групп и проверка их количества")
    public ProfileSteps findActualGroupFilter() {
        actualGroupInProfile.shouldHaveSize(4);
        for (SelenideElement element : actualGroupInProfile) {
            element.click();
            bounceWait.shouldBe(disappear);
        }
        return this;
    }

    @Step("Открытие друзей у выбранного пользователя")
    public ProfileSteps selectFriendsInProfile() {
        selectFriendsInPrifle.click();
        return this;
    }

    @Step("Проверка загрузки раздела Друзья у группы")
    public ProfileSteps checkFriendsInProfilesLoading() {
        profileFriendsBody.shouldBe(visible);
        bounceWait.shouldBe(hidden);
        return this;
    }

    @Step("Открытие ретроспективы у  выбранного пользователя")
    public ProfileSteps selectRetroProfile() {
        selectRetroInPrifle.click();
        return this;
    }

    @Step("Проверка загрузки данных по ретроспективе профиля")
    public ProfileSteps checkRetroProfle() {
        retroData.shouldBe(visible);
        return this;
    }

    @Step("Открытие Аналатики у выбранного пользователя")
    public ProfileSteps selectAnalyticsPrifile() {
        selectAnalyticsInPrifle.click();
        return this;
    }

    @Step("Просмотр загрузки данных по аналатике профиля")
    public ProfileSteps checkAnalyticsInProfile() {
        analyticsData.shouldBe(visible);
        return this;
    }

    @Step("Ввод даты даты BSTM")
    public ProfileSteps setValueDataBSTM(String dataBSTM) {
        inputBSTM.val(dataBSTM);
        return this;
    }

    @Step("Применение результатов поиска по дате BSTM")
    public ProfileSteps applyResultBSTM() {
        buttonCreateReport.get(1).shouldNotHave(disabled).click();
        return this;
    }

    @Step("Проверка, что страниица содержит указанную дату")
    public ProfileSteps checkResultBSTM() {
        $(byText("12.12.2020")).shouldBe(visible);
        return this;
    }

    @Step("Проверка доступных для выбора  статусов профиля по Аналатике")
    public ProfileSteps checkProfileStatusAnalytics() {
        analyticsProfileSelectDate.shouldHaveSize(4);
        commentAnalytics.shouldHave(not(disabled));
        buttonSave.shouldHave(not(disabled));
        return this;
    }

    @Step("Проверка возможности выбора статусов аналаза и ввод текста для проверки")
    public ProfileSteps checkSelectStatusAnalytics() {
        for (SelenideElement elementAnalytics : analyticsProfileSelect) {
            elementAnalytics.click();
            commentAnalytics.val(faker.random().toString());
            buttonSave.click();
            commentAnalytics.clear();
        }
        return this;
    }

    @Step("Открытие событий")
    public ProfileSteps openEventProfile() {
        selectEventInPrifle.click();
        return this;
    }

    @Step("Проверка доступности выборки даты для отчетов")
    public ProfileSteps checkSelectDateReport() {
        dateByEvent.shouldHaveSize(4);
        for (SelenideElement element : dateByEvent) {
            element.shouldHave(not(disabled));
        }
        periodEventProfile.shouldHave(not(disabled));
        for (SelenideElement elementPeriod : analyticsProfileSelectPeriod) {
            elementPeriod.click();
        }
        return this;
    }
}