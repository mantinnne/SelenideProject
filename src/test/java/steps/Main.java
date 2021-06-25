package steps;

import io.qameta.allure.Step;

public class Main {

    @Step("Name steps")
    public Main openDossier(String namePage) {
        return this;
    }

    @Step("Name steps")
    public Main filterLastName(String lastName) {
        return this;
    }

    @Step("Name steps")
    public Main filterFirstName(String firstName) {
        return this;
    }

    @Step("Name steps")
    public Main filterMiddleName(String middleName) {
        return this;
    }

    @Step("Name steps")
    public Main filterHappyDay(int day) {
        return this;

    }

    @Step("Name steps")
    public Main inputNumberPassport(String passport) {
        return this;
    }

}
