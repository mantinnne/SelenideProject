package Methud.privat;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainSteps {
    final ElementsCollection elements = $$(".values-count"),
            profileListViev = $$(".profile-info-container");

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

}
