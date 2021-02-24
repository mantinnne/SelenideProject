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

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class ProfilePage extends BasePrivat {

    final SelenideElement profileData = $(".profile-main-data");
    final SelenideElement groupData = $("div.profile-component-container");
    final SelenideElement friendsData = $(".friends-list");
    final SelenideElement retroData = $(".profile-retro__data");
    final SelenideElement analyticsData = $(".profile-analytics");
    final SelenideElement monitoringData = $(".profile-anomalies");
    final SelenideElement activityData = $(".profile__activity");
    final SelenideElement createReportFild = $(".toasted-primary");
    final SelenideElement bounceWait = $(".double-bounce2");
    final SelenideElement dectructive = $(".vs__selected-options");
    final SelenideElement profileGroupBody = $(".report-publics__container");
    final SelenideElement activityProfile = $(".activity__feed");
    final SelenideElement profileFriendsBody = $(".profile-component");
    final SelenideElement selectGroupInPrifle = $(".link-container").$(byText("Группы"));
    final SelenideElement selectFriendsInPrifle = $(".link-container").$(byText("Друзья"));
    final SelenideElement selectRetroInPrifle = $(".link-container").$(byText("Ретроспектива"));
    final SelenideElement selectAnalyticsInPrifle = $(".link-container").$(byText("Аналитика"));
    final SelenideElement selectEventInPrifle = $(byText("События"));
    final SelenideElement selectActivityInPrifle = $(byText("Активность"));
    final SelenideElement inputBSTM = $("#bstm");
    final SelenideElement buttonSave = $(".app-button");
    final SelenideElement commentAnalytics = $(".analytics__comment");
    final SelenideElement periodEventProfile = $("select.custom-input");
    final SelenideElement tableFromProfileActivity = $(".post-table");


    final ElementsCollection linkProfile = $$(".name-link > span");
    final ElementsCollection prifileNavigationList = $$x("//div[@class='link-container']/a");
    final ElementsCollection buttonCreateReport = $$(".app-button");
    final ElementsCollection direction = $$(".vs__dropdown-option");
    final ElementsCollection actualGroupInProfile = $$(byText("Только актуальные"));
    final ElementsCollection dateByEvent = $$("input[type='date']");
    final ElementsCollection analyticsProfileSelectDate = $$(".analytics__select > option");
    final ElementsCollection analyticsProfileSelectPeriod = $$(".custom-input > option");

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Выбор личной страницы профиля")
    public void selectProfile() {
        step("Выбор личной страницы профиля и переход на неё", () -> linkProfile.shouldHaveSize(10).first().click());
        step("Проверка открытого профиля и данны по нему", () -> {
            profileData.shouldBe(visible);
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Навигация по данным профиля")
    void profileNavigation() {
        step("Выбор профиля", this::selectProfile);

        step("Определенного раздела", () -> prifileNavigationList.get(2).click());
        step("Перебор страниц профиля", () -> {
            for (SelenideElement element : prifileNavigationList) {
                element.click();
                step("Проверка, что у выбранного раздела есть класс nuxt-link-active", () -> {
                    element.shouldHave(cssClass("nuxt-link-active"));

                });
                step("Проверка загрузки страницы Профиля", () -> {
                    String textFildNavigationList = element.getText();

                    switch (textFildNavigationList) {
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
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Скачивание отчета по пользователю")
    void profileDownloadReport() {

        step("Выбор профиля", this::selectProfile);

        step("Выбор отчета и скачивание его", () -> {
            for (SelenideElement element : buttonCreateReport) {
                element.click();
                step("Ожидание появляения окна отчета и его скрытие", () -> {
                    createReportFild.shouldBe(visible);
                    createReportFild.shouldBe(disappear);
                });
            }
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Отображение методики у пользователя")
    void profileSelectMetodic() {
        step("Выбор профиля", this::selectProfile);
        step("Проверка, что сброс методоки неактивен", () -> {
            refresh();
            ElementsCollection collection = $$x("//select[@class='metodic__select-metodic']//option");
            collection.first().shouldBe(disabled);
            step("Выборка методки у пользователя", () -> {
                for (int i = 1; i < collection.size(); i++) {
                    collection.get(i).click();
                    bounceWait.shouldBe(disappear);
                }
            });
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Low")})
    @DisplayName("Открытие ссылки на профиль vk у профиля")
    void profleRedirectVK() {
        step("Выбор профиля", this::selectProfile);
        step("нахождение ссылки у профиля ", () -> $x("//span/a").click());
        step("Переключение на старницу VK и проверка открытия профиля", () -> {
            switchTo().window(1);
            $("title").shouldHave(attribute("text", "Елена Макарова | ВКонтакте"));
            closeWindow();
            switchTo().window(0);
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Посмотр групп у профиля и фильтрация по направлениям")
    void profileFilterGroupDestructive() {
        profileFilterGroup(26, 27, 0);
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Посмотр групп у профиля и фильтрация по направлениям")
    void profileFilterGroupOpozition() {
        profileFilterGroup(19, 20, 1);
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Посмотр групп у профиля и фильтрация по направлениям")
    void profileFilterGroupPolitical() {
        profileFilterGroup(3, 4, 2);
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Посмотр групп у профиля и фильтрация по направлениям")
    void profileFilterGroupPositive() {
        profileFilterGroup(14, 15, 3);
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Выбор актуальных групп у профиля")
    void profileSelectActualGroupInProfile() {
        step("Выбор профиля", this::selectProfile);
        step("Открытие групп у  выбранного пользователя", (Allure.ThrowableRunnableVoid) selectGroupInPrifle::click);
        step("Нахождение выборки актуальных групп и проверка их количества", () -> {
            actualGroupInProfile.shouldHaveSize(4)
            ;
            step("Выбор актуальных групп у профиля", () -> {
                for (SelenideElement element : actualGroupInProfile) {
                    element.click();
                    bounceWait.shouldBe(disappear);

                }
            });
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Low")})
    @DisplayName("Просмотр друзей у профиля и фильтрация по направлениям")
    void profileFilterFrieds() {
        step("Выбор профиля", this::selectProfile);
        step("Открытие друзей у  выбранного пользователя", (Allure.ThrowableRunnableVoid) selectFriendsInPrifle::click);
        step("Проверка загрузки раздела группы", () -> {
            profileFriendsBody.shouldBe(visible);
            bounceWait.shouldBe(hidden);
        });
        step("Выбор сортировки друзей по направлениям и проверка количества направлений для фильтрации", () -> {
            dectructive.click();
            direction.shouldHaveSize(62);
        });
        step("Проверка появления кнопки сброса фильтрации при выборе фильтра", () -> {
            direction.first().click();
            dectructive.click();
            bounceWait.shouldBe(hidden);
            direction.shouldHaveSize(63);
        });
        step("Выбор направлений для сортировки друзей у профиля", () -> {
            for (int i = 1; i < direction.size(); i++) {
                direction.get(i).click();
                profileFriendsBody.shouldBe(visible);
                bounceWait.shouldBe(hidden);
                dectructive.click();
                direction.get(i).shouldHave(cssClass("vs__dropdown-option--selected"));
            }
        });
        step("Сброс примененных фильтров", () -> {
            direction.get(0).click();
            profileFriendsBody.shouldBe(visible);
        });
    }


    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Просмотр ретроспективы у профиля")
    void profleCheckRetroProfile() {
        step("Выбор профиля", this::selectProfile);
        step("Открытие ретроспективы у  выбранного пользователя", (Allure.ThrowableRunnableVoid) selectRetroInPrifle::click);
        step("Проверка загрузки данных по ретроспективе профиля", () -> retroData.shouldBe(visible));
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Просмотр Аналитики у профиля")
    void profleCheckAnalyticsProfile() {
        step("Выбор профиля", this::selectProfile);
        step("Открытие Аналитики у  выбранного пользователя", (Allure.ThrowableRunnableVoid) selectAnalyticsInPrifle::click);
        step("Просмотр загрузки данных по аналатике профиля", () -> analyticsData.shouldBe(visible));
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Установка даты отправки в BSTM")
    void profileDataOutBSTM() {
        step("Выбор профиля", this::selectProfile);
        step("Открытие Аналитики у  выбранного пользователя", (Allure.ThrowableRunnableVoid) selectAnalyticsInPrifle::click);
        step("Ввод в инпут даты и применение результа", () -> {
            inputBSTM.val("12192020");
            buttonCreateReport.get(1).shouldNotHave(disabled).click();
        });
        step("проверка, что страниица содержит указанную дату", () -> $(byText("12.12.2020")).shouldBe(visible));
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Аналитика по профилю")
    void analyticsProfile() {
        step("Выбор профиля", this::selectProfile);
        step("Открытие аналитики у  выбранного пользователя", (Allure.ThrowableRunnableVoid) selectAnalyticsInPrifle::click);
        step("Проверка доступных для выбора  статусов профиля по Аналатике", () -> {
            analyticsProfileSelectDate.shouldHaveSize(4);
            commentAnalytics.shouldHave(not(disabled));
            buttonSave.shouldHave(not(disabled));
        });
/*        step("Проверка возможности выбора статусов аналаза и ввод текста для проверки", () -> {

            for (SelenideElement elementAnalytics : analyticsProfileSelect) {
                elementAnalytics.click();
                commentAnalytics.val(faker.random().toString());
                buttonSave.click();
                commentAnalytics.clear();
            }
        });*/
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("События по профилю")
    void profileEvent() {
        step("Выбор профиля", this::selectProfile);
        step("Открытие событий у  выбранного пользователя", (Allure.ThrowableRunnableVoid) selectEventInPrifle::click);
        step("Проверка доступности выборки даты для отчетов", () -> {
            dateByEvent.shouldHaveSize(4);
            for (SelenideElement element : dateByEvent) {
                element.shouldHave(not(disabled));
            }
            periodEventProfile.shouldHave(not(disabled));
            for (SelenideElement elementPeriod : analyticsProfileSelectPeriod) {
                elementPeriod.click();

            }
        });
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


    public void profileFilterGroup(int size, int fullSize, int number) {
        SelenideElement dectructive = $(".vs__selected-options", number);

        step("Выбор профиля", this::selectProfile);
        step("Открытие групп у  выбранного пользователя", (Allure.ThrowableRunnableVoid) selectGroupInPrifle::click);
        step("Проверка загрузки раздела группы", () -> {
            profileGroupBody.shouldBe(visible);
        });
        step("Выбор сортировки групп по направлениям деструктива  и проверка количества направлений деструктива  для фильтрации", () -> {
            dectructive.click();
            direction.shouldHaveSize(size);
        });
        step("Проверка появления кнопки сброса фильтрации при выборе фильтра", () -> {
            direction.first().click();
            dectructive.click();
            direction.shouldHaveSize(fullSize);
        });
        step("Выбор направлений деструктива для сортировки групп у профиля", () -> {
            for (int i = 1; i < direction.size(); i++) {
                direction.get(i).click();
                profileGroupBody.shouldBe(visible);
                dectructive.click();
                direction.get(i).shouldHave(cssClass("vs__dropdown-option--selected"));
            }
        });
        step("Сброс примененных фильтров", () -> {
            direction.get(0).click();
            profileGroupBody.shouldBe(visible);
        });
    }

}
