package oko;

import Methud.Oko.MainSteps;
import config.BaseOko;
import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class MainPage extends BaseOko {
    MainSteps steps = new MainSteps();

    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Oko"), @Tag("Medium")})
    @DisplayName("Открытие главной страницы")
    void waitLoadingMainPage() {
        steps.loadingPageMain();
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Oko")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Oko"), @Tag("Medium")})
    @DisplayName("Сортировка по направлениям на главной стрнице")
    void checkSelectDirection() {
        steps.selectDirection();

    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Story("Oko")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Oko"), @Tag("Low")})
    @DisplayName("Увелечение и уменьшение зума у карты")
    void checkZoomMainPage() {
        steps.zoom();

    }
}
