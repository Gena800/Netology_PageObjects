package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {

    private ElementsCollection heading = $$("[data-test-id='action-deposit']");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";



    public TransferPage selectCardToTransfer(int id) {
        heading.get(id).click();
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


}