package privat;

import Methud.privat.ProfileSteps;
import com.codeborne.selenide.ElementsCollection;
import config.BasePrivat;
import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class ProfilePage extends BasePrivat {

    ProfileSteps steps = new ProfileSteps();
    final String nameProfile = "Макарова Елена";
    final ElementsCollection
            direction = $$(".vs__dropdown-option");

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
    @DisplayName("Активные комментарии пользователя")
    void profileActivityComment() {
        steps.selectProfilePage(nameProfile);
        steps.openActivityProfile();
        steps.selectCommentProfile();
        steps.checkCommentProfile();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Проверка отображения текста, при не   найденных комментариях")
    void profileActivityCommentNotFind() {
        steps.selectProfile("2");
        steps.openActivityProfile();
        steps.selectCommentProfile();
        steps.checkCommentProfileNotFind();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Проверка отображения текста, при не найденных постах")
    void profileActivityPostNotFind() {
        steps.selectProfilePage(nameProfile);
        steps.openActivityProfile();
        steps.selectPostProfile();
        steps.checkTextNotFindPost();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Проверка отображения текста, при  найденных постах")
    void profileActivityPostFind() {
        steps.selectProfile("262687131");
        steps.openActivityProfile();
        steps.selectPostProfile();
        steps.checkTextFindPost();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Проверка отображения текста, при не найденных фото")
    void profileActivityPhotoNotFind() {
        steps.selectProfilePage(nameProfile);
        steps.openActivityProfile();
        steps.selectPhotoProfile();
        steps.checkTextNotFindPhoto();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Проверка отображения контекта, который профиль лайкнул")
    void profileActivityLikes() {
        steps.selectProfile("447");
        steps.openActivityProfile();
        steps.selectLikes();
        steps.checkSelectLikes();
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Проверка отображения текста о том, что контента по лайкам нет у пользователя")
    void profileActivityLikesNotFind() {
        try {
            steps.selectProfile("19494");
            steps.openActivityProfile();
            steps.selectLikes();
            steps.checkSelectLikesNotFind();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
        }

    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Проверка отображения другого контента у пользователя")
    void profileActivityOtherContent() {
        steps.selectProfile("498505175");
        steps.openActivityProfile();
        steps.selectOther();
        steps.checkSelectOtherContent();
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("low")})
    @DisplayName("Проверка отображения надписи о не найденном  другом контенте  у пользователя ")
    void profileActivityOtherContentNotFind() {
        steps.selectProfile("163316772");
        steps.openActivityProfile();
        steps.selectOther();
        steps.checkSelectOtherContentNotFind();
    }

    @Test
    void lambaTest() {
        step("Открытие страницы", () -> open("google.ru"));
        $("#input").as("Кнопка ввода");
        $("#input").as("Кнопка ввода -> #input");
    }

}
