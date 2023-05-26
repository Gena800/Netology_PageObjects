package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {

    private ElementsCollection heading = $$("[data-test-id='action-deposit']");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement heading1 = $("[data-test-id=dashboard]");
    private SelenideElement errorMessage = $("[data-test-id=error-notification] .notification__content");

    public void verifyIsDashboardPage() {
        heading1.shouldBe(visible);
    }


    public TransferPage selectCardToTransfer(int indexCardTo) {
        heading.get(indexCardTo).click();
        return new TransferPage();
    }

    public int getCardBalance(String id) {
        var text = cards.get(Integer.parseInt(id)).text();
        return extractBalance(text);
    }


    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void shouldErrorMessage() {
        errorMessage.shouldHave(exactText("Ошибка! Недостаточно средств на карте."));
    }

}
