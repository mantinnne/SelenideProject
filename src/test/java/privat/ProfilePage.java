package privat;

import Methud.privat.ProfileSteps;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.BasePrivat;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class ProfilePage extends BasePrivat {

    ProfileSteps steps = new ProfileSteps();

    final String nameProfile = "Макарова Елена";

    final SelenideElement
            dectructive = $(".vs__selected-options"),
            activityProfile = $(".activity__feed"),
            selectAnalyticsInPrifle = $(".link-container").$(byText("Аналитика")),
            selectEventInPrifle = $(byText("События")),
            selectActivityInPrifle = $(byText("Активность")),
            buttonSave = $(".app-button"),
            commentAnalytics = $(".analytics__comment"),
            periodEventProfile = $("select.custom-input"),
            tableFromProfileActivity = $(".post-table");


    final ElementsCollection
            direction = $$(".vs__dropdown-option"),
            dateByEvent = $$("input[type='date']"),
            analyticsProfileSelectDate = $$(".analytics__select > option"),
            analyticsProfileSelectPeriod = $$(".custom-input > option");

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Выбор личной страницы профиля")
    public void selectProfile() {
        steps.selectProfilePage(nameProfile);
        steps.checkSelectProfilePage(nameProfile);
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Навигация по данным профиля")
    void profileNavigation() {
        steps.selectProfilePage(nameProfile);
        steps.selectDefinitelyProfile();
        steps.navigationSectionProfile();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Скачивание отчета по пользователю")
    void profileDownloadReport() {
        steps.selectProfilePage(nameProfile);
        steps.downloadReportProfile();

    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Отображение методики у пользователя")
    void profileSelectMetodic() {
        steps.selectProfilePage(nameProfile);
        steps.checkInactiveResetMetodic();
        steps.selectMetodicIsProfile();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Low")})
    @DisplayName("Открытие ссылки на профиль vk у профиля")
    void profleRedirectVK() {
        steps.openLinkProfileVK();
        steps.switchWindows(1);
        steps.checkOpenProfileVk();
        steps.switchWindows(0);
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Посмотр групп у профиля и фильтрация по направлениям")
    void profileFilterGroupDestructive() {
        steps.selectProfilePage(nameProfile);
        steps.selectGroupInProfile();
        steps.checkLoadingGroupProfile();
        steps.checkResetFilter(direction, 26, 27, 0);
        steps.selectAllDirectionGroupFilter(direction, 0);

    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Посмотр групп у профиля и фильтрация по направлениям")
    void profileFilterGroupOpozition() {
        steps.selectProfilePage(nameProfile);
        steps.selectGroupInProfile();
        steps.checkLoadingGroupProfile();
        steps.checkResetFilter(direction, 19, 20, 1);
        steps.selectAllDirectionGroupFilter(direction, 1);
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Посмотр групп у профиля и фильтрация по направлениям")
    void profileFilterGroupPolitical() {
        steps.selectProfilePage(nameProfile);
        steps.selectGroupInProfile();
        steps.checkLoadingGroupProfile();
        steps.checkResetFilter(direction, 4, 5, 2);
        steps.selectAllDirectionGroupFilter(direction, 2);
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Посмотр групп у профиля и фильтрация по направлениям")
    void profileFilterGroupPositive() {
        steps.selectProfilePage(nameProfile);
        steps.selectGroupInProfile();
        steps.checkLoadingGroupProfile();
        steps.checkResetFilter(direction, 14, 15, 3);
        steps.selectAllDirectionGroupFilter(direction, 3);
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Выбор актуальных групп у профиля")
    void profileSelectActualGroupInProfile() {
        steps.selectProfilePage(nameProfile);
        steps.selectGroupInProfile();
        steps.findActualGroupFilter();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Low")})
    @DisplayName("Просмотр друзей у профиля и фильтрация по направлениям")
    void profileFilterFrieds() {
        steps.selectProfilePage(nameProfile);
        steps.selectFriendsInProfile();
        steps.checkFriendsInProfilesLoading();
        steps.checkResetFilter(direction, 63, 64, 0);
        steps.selectAllDirectionGroupFilter(direction, 0);

    }


    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Просмотр ретроспективы у профиля")
    void profleCheckRetroProfile() {
        steps.selectProfilePage(nameProfile);
        steps.selectRetroProfile();
        steps.checkRetroProfle();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Просмотр Аналитики у профиля")
    void profleCheckAnalyticsProfile() {
        steps.selectProfilePage(nameProfile);
        steps.selectAnalyticsPrifile();
        steps.checkAnalyticsInProfile();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Установка даты отправки в BSTM")
    void profileDataOutBSTM() {
        steps.selectProfilePage(nameProfile);
        steps.selectAnalyticsPrifile();
        steps.setValueDataBSTM("12192020");
        steps.applyResultBSTM();
        steps.checkResultBSTM();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Аналитика по профилю")
    void analyticsProfile() {
        steps.selectProfilePage(nameProfile);
        steps.selectAnalyticsPrifile();
        steps.checkProfileStatusAnalytics();
        steps.checkSelectStatusAnalytics();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("События по профилю")
    void profileEvent() {
        steps.selectProfilePage(nameProfile);
        steps.openEventProfile();
        steps.checkSelectDateReport();


            }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Активность  по профилю сортировка постов")
    void profileActivityPost() {
        step("Выбор профиля", this::selectProfile);
        step("Открытие событий у  выбранного пользователя", (Allure.ThrowableRunnableVoid) selectActivityInPrifle::click);
        step("Выбор сортировки групп по направлениям и проверка количества направлений для фильтрации", () -> {
            dectructive.click();
            direction.shouldHaveSize(2);
        });
        step("Проверка появления кнопки сброса фильтрации при выборе фильтра", () -> {
            direction.first().click();
            dectructive.click();
            direction.shouldHaveSize(3);
        });
        step("Выбор направлений для сортировки групп у профиля", () -> {
            for (int i = 1; i < direction.size(); i++) {
                direction.get(i).click();
                activityProfile.shouldBe(visible);
                dectructive.click();
                direction.get(i).shouldHave(cssClass("vs__dropdown-option--selected"));
            }
        });
        step("Сброс примененных фильтров", () -> {
            direction.get(0).click();
            activityProfile.shouldBe(visible);
        });
    }

    @Test
    @Story("Privat")
    @Disabled("Пагинация по комментариям у профиля")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Активные комментарии пользователя")
    void profileActivityComment() {
        step("Выбор профиля", this::selectProfile);
        step("Открытие событий у  выбранного пользователя", (Allure.ThrowableRunnableVoid) selectActivityInPrifle::click);
        step("Переход в активные комментарии пользователя", () -> $(byText("Комментарии")).click());
        step("Проверка загрузки таблицы с комментариями у выбранного пользователя", () -> tableFromProfileActivity.shouldHave(visible));
    }

    @Test
    @Disabled("Пагинация у профиля в стевой активности по лайкам")
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Активные лайки пользователя")
    void profileActivityLikes() {
        step("Выбор профиля", this::selectProfile);
        step("Открытие событий у  выбранного пользователя", (Allure.ThrowableRunnableVoid) selectActivityInPrifle::click);
        step("Переход в активные комментарии пользователя", () -> $(byText("Лайки")).click());
//        step("Проверка отображения лайков у пользователя ", () -> );


    }
}
