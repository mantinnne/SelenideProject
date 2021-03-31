package oko;

import Methud.Oko.RegionSteps;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.BaseOko;
import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class UniverPage extends BaseOko {
    RegionSteps steps = new RegionSteps();
    RegionPage regionPage = new RegionPage();

    final String nameUniver = "Баумана";
    ElementsCollection bodyUniverName = $$(".university-name"),
            districtUniversiteties = $$(".group-panel");


    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("medium")})
    @DisplayName("Поиск университета в регионе")
    void seachUniversities() {
        steps.selectRegion("Москва");
        steps.checkSelectRegion("Москва");
        step("Переход на раздел Университеты", () -> $(byLinkText("Университеты")).click());
        step("Ввод название университета для поиска", () -> $x("//input[@class='app-input plain size-lg']").val(nameUniver));
        step("Открытие округа региона для отображения университетов", () -> $(".group-panel").click());
        step("Проверка нахождения университета из поиска и количество отображаемых университетов", () -> {
            bodyUniverName.first().shouldBe(text(nameUniver));
            $$(".university").shouldHaveSize(1);
        });
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("Oko"), @Tag("Web"), @Tag("medium")})
    @DisplayName("Выбор университета в регионе")
    void regionSelectUniversities() {
        steps.selectRegion("Москва");
        steps.checkSelectRegion("Москва");
        step("Переход на раздел Университеты", () -> $(byLinkText("Университеты")).click());
        sleep(500);
        step("Открытие округов университетов по округу", () -> {
            for (SelenideElement selenideElement : districtUniversiteties) {
                selenideElement.click();
                step("Проверка появления класса expanded у открытого округа", () -> selenideElement.shouldHave(cssClass("expanded")));
            }
        });
        step("Просмотр доступных университетов и открытие первого доступного университета", () -> bodyUniverName.first().click());
        step("Проверка загрузки данных по университету", () -> $(".university-body").shouldBe(visible));
    }
}
