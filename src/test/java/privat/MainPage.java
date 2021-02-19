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

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class MainPage extends BasePrivat {


    final String profileList = ".profile-line-block",
            regionFilter = "div#vs1__combobox",
            directionFilter = "div#vs2__combobox",
            cosialNetworkFilter = "div#vs3__combobox",
            metodicFilter = "div#vs4__combobox",
            conutGroupFilter = "div#vs5__combobox",
            conutGroupFilterList = "//ul[@id='vs5__listbox']//li",
            metodicFilterList = "//ul[@id='vs4__listbox']//li",
            cosialNetworkFilterList = "//ul[@id='vs3__listbox']//li",
            regionFilterList = "//ul[@id='vs1__listbox']//li",
            directionFilterList = "//ul[@id='vs2__listbox']//li",
            buttonFilter = ".app-button",
            idFilterFild = ".filter-input",
            textAreas = "textarea",
            dropdownSelected = "vs__dropdown-option--selected";


    final ElementsCollection elements = $$(".values-count"),
            profileListViev = $$(profileList),
            trafficValues = $$(".values-count"),
            filterChechBox = $$("label.checkbox-label"),
            filterList = $$x(regionFilterList),
            profile = $$(profileList),
            directionList = $$x(directionFilterList),
            cosialNetworkList = $$x(cosialNetworkFilterList),
            metodicList = $$x(metodicFilterList);


    final SelenideElement profilesAggRatings = $(".profiles-agg-ratings"),
            region = $(regionFilter),
            idFilter = $(idFilterFild),
            button = $(buttonFilter),
            direction = $(directionFilter),
            cosialNetwork = $(cosialNetworkFilter),
            metodic = $(metodicFilter),
            minRating = $("#min-range"),
            maxRating = $("#max-range"),
            textArea = $(textAreas),
            buttonSave = $(withText("Сохранить"));


    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Загрузка допольнительных профилей")
    void loadingMoreProfile() {
        step("Проверка отображения доступных профилей", () -> {
            profileListViev.shouldHaveSize(10);
        });
        step("Нажатие на кнопку загрузки допольнительных профилей", (Allure.ThrowableRunnableVoid) $(".button-load-more")::click);
        step("Проверка отображение увелеченного количества профилей на странице", () -> {
            profileListViev.shouldHaveSize(20);
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка отображение загрузки светофора профилей и проверка их неравенства null")
    void profileTrafficlight() {
        step("Провека отображение светофора", () -> {
            profilesAggRatings.shouldBe(visible, Duration.ofSeconds(15));

        });
        step("Проверка значения у светофоров", () -> {
            for (SelenideElement element : trafficValues) {
                String text = element.getText();
                assertNotEquals(null, text);
            }
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка доступности выборки фильтров забаненные/удаленные")
    void checkFilterDeletedOrBanProfile() {
        step("Проверка возможности выбрать фильтрацию по забанннеым / удаленным профилям", () -> {
            for (SelenideElement chechBox : filterChechBox) {
                chechBox.click();
            }
        });


    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка фильтрации профилей по региону")
    void regionFilterProfile() {

        step("Выбор фильтра по региону и проверка количества регионов для фильтрации", () -> {
            region.click();
            filterList.shouldHaveSize(85);
        });
        step("Выбор доступного региона у фильтра и проверка отображение кнопки для сброса фильтров", () -> {
            filterList.get(1).click();
            region.click();
            filterList.shouldHaveSize(86);
        });
        step("Перебор значений фильтрации по региону", () -> {

            for (int i = 1; i < filterList.size(); i++) {
                filterList.get(i).click();
                $(profileList).shouldBe(visible);
                region.click();
                $(filterList.get(i)).shouldHave(cssClass(dropdownSelected));
                region.click();
            }
        });
        step("Сброс значения фильтров", () -> {
            region.click();
            filterList.get(0).click();
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка фильтрации профилей по id профиля")
    void idFilterProfile() {
        step("Ввод в поле сортировки профилей случайного id профиля и нажатие на кнопку поиска", () -> {

            idFilter.val(String.valueOf(faker.random().nextInt(1, 1000)));
            button.click();
        });

        step("Проверка результатов поиска по id пользователя", () -> {
            profile.shouldHaveSize(1);

        });
        step("Очистка поля поиска", () -> {
            idFilter.clear();
            button.click();
        });
        step("Проверка поиска профилей по с указанием нескольких id", () -> {
            int counter = faker.random().nextInt(1, 10);
            for (int i = 1; i <= counter; i++) {
                Integer numberID = faker.random().nextInt(1, 10000);
                if (i < counter) {
                    idFilter.append(numberID + ",");
                } else {
                    idFilter.append(String.valueOf(numberID));
                }
            }
            button.click();
            step("Проверка отображения профилей по поиску с указанием нескольких id", () -> {
                profile.shouldHaveSize(counter);
            });
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка фильтрации профилей напарвлениям профиля и по количеству групп в направлении")
    void directionFilterProfile() {
        step("Выборка фильтра по напрвлениям", (Allure.ThrowableRunnableVoid) direction::click);
        step("Проверка доступных фильтров для выбора", () -> {
            directionList.shouldHaveSize(45);
        });
        step("Выбор доступного фильтра", () -> directionList.get(1).click());
        step("Проверка появления кнопки для сброса фильтров", () -> {
            direction.click();
            directionList.shouldHaveSize(46);
        });

        step("Перебор доступных фильтров по направлениям", () -> {
            for (int i = 1; i < directionList.size(); i++) {
                directionList.get(i).click();
                direction.click();
                int finalI = i;
                step("Проверка что выбранное направление имеет класс dropdownSelected", () -> {
                    $(directionList.get(finalI)).shouldHave(cssClass(dropdownSelected));
                });
                step("Выбор количества групп у профиля по выбранному направлению", () -> {
                    countGroupDirection();
                    direction.click();
                });
            }
        });
        step("Сброс значения фильтра", () -> {
            direction.click();
            directionList.get(0).click();
        });

    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка фильтрации профилей по социальной  сети")
    void cosialNetworkFilterProfile() {
        step("Выбор фильтра социальной сети ", (Allure.ThrowableRunnableVoid) cosialNetwork::click);
        step("Проверка доступных для выбора фильтров по социальнойй сети", () -> {
            cosialNetworkList.shouldHaveSize(5);
        });
        step("Выбор доступного фильтра", () -> cosialNetworkList.get(1).click());
        step("Проверка отображения кнопки для сброса фильтра", () -> {
            cosialNetwork.click();
            cosialNetworkList.shouldHaveSize(6);
        });
        step("Перебор значений для фильтрации", () -> {
            for (int i = 1; i < cosialNetworkList.size(); i++) {
                cosialNetworkList.get(i).click();
                int finalI = i;
                step("Проверка выбранного фильтра на содержание класса dropdownSelected", () -> {
                    cosialNetwork.click();
                    $(cosialNetworkList.get(finalI)).shouldHave(cssClass(dropdownSelected));
                });
            }
        });
        step("Сброс значения фильтра", () -> {
            cosialNetwork.click();
            cosialNetworkList.get(0).click();
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Medium")})
    @DisplayName("Проверка фильтрации профилей по методикам и рейтингу")
    void metodifFilterProfile() {
        step("Выбор методики для фильтрации",
                (Allure.ThrowableRunnableVoid) metodic::click);
        step("Проверка количества фильтров для фильтрации", () -> {
            metodicList.shouldHaveSize(2);
        });
        step("Выбор доступного фильтра", () -> metodicList.get(1).click());
        step("Проверка появления кнопки для сброса", () -> {
            metodic.click();
            metodicList.shouldHaveSize(3);
        });
        step("Выбор рейтинга в фильтре по минимальному значению", () -> {
            actions().moveToElement(minRating).clickAndHold().perform();
            actions().moveToElement(minRating, 0, 100).release().perform();

        });
        step("Выбор рейтинга в фильтре по макимальному  значению", () -> {
            actions().moveToElement(maxRating).clickAndHold().perform();
            actions().moveToElement(maxRating, 0, -100).release().perform();

        });
        step("Перебор направления и выбор сортировки по рейтингу", () -> {
            for (int i = 1; i < metodicList.size(); i++) {
                metodicList.get(i).click();
                metodic.click();
                int finalI = i;
                step("Проверка выбранного фильтра на содержание класса dropdownSelected", () -> {
                    $(metodicList.get(finalI)).shouldHave(cssClass(dropdownSelected));
                });

            }
        });
        step("Сброс фильтра", () -> {
            metodic.click();
            metodicList.get(0).click();
        });
    }


    void countGroupDirection() {

        SelenideElement countGroup = $(conutGroupFilter);
        ElementsCollection countGroupList = $$x(conutGroupFilterList);

        countGroup.click();
        countGroupList.shouldHaveSize(4);
        countGroupList.get(1).click();
        countGroup.click();
        countGroupList.shouldHaveSize(5);

        for (int i = 1; i < countGroupList.size(); i++) {
            profilesAggRatings.shouldBe(visible, Duration.ofSeconds(15));
            for (SelenideElement element : elements) {
                String text = element.getText();
                if (text.equals("0")) {
                    continue;
                }
                countGroupList.get(i).click();
                countGroup.click();
                $(countGroupList.get(i)).shouldHave(cssClass(dropdownSelected));
            }
        }
        countGroup.click();
        countGroupList.get(0).click();

    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("Low")})
    @DisplayName("Скрытие фильтров")
    void hiddenFilter() {
        step("Скрытие фильтров для отображение для профиоей", () -> $(byLinkText("Меньше фильтров")).click());
        step("Проверка, что были скрыты поля для фильтрации", () -> {
            $(regionFilter).shouldHave(disappear);
            $(directionFilter).shouldHave(disappear);
            $(conutGroupFilter).shouldHave(disappear);
            $(metodicFilter).shouldHave(disappear);
            $(idFilterFild).shouldHave(disappear);
            $(conutGroupFilter).shouldHave(disappear);
        });
    }

    @Test
    @Story("Privat")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("Privat"), @Tag("High")})
    @DisplayName("Быстрый поиск профилей")
    void fastSeachProfileFilter() {

        step("Выбор быстрой фильтрации профилей", () -> $(byLinkText("Быстрая проверка")).click());
        step("Проверка, доступности окна для фильтрации профилей", () -> {
            $(".profiles-check").shouldBe(visible);

        });
        step("Геренация рандомных id профилей для проверки поиска", () -> {
            int counter = faker.random().nextInt(10, 30);
            step("Ввод рандомных значений профилей для поиска", () -> {
                for (int i = 0; i <= counter; i++) {
                    Integer numberID = faker.random().nextInt(1, 10000);
                    textArea.append(numberID + "\n");
                }
            });
        });
        step("Нажатие на кнопку применения результатов", () -> $(buttonFilter).click());
        step("Ожидание загрузки профилей", () -> {
            $(".list").shouldBe(visible);
            $(".double-bounce2").shouldBe(disappear, Duration.ofSeconds(20));
        });
        step("Выборка первого id не из БЗ", () -> $(byText("Не добавлять")).click());
        step("Добавление пользователя в БЗ", () -> {
            buttonSave.click();
            buttonSave.shouldBe(disappear);
        });

    }
}