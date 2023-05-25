package ru.netology.web.test;



import org.junit.jupiter.api.Test;

import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;

import ru.netology.web.page.LoginPageV2;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



class MoneyTransferTest {


    @Test
    void shouldNotLogin() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        loginPage.unValidLogin(DataHelper.getAuthInfoWrong()).shouldErrorMessage();
    }

    @Test
    void shouldNotVerify() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getUnVerificationCodeFor(authInfo);

        loginPage.validLogin(authInfo).unValidVerify(verificationCode).shouldErrorMessage();
    }

    @Test
    void shouldTransferCashFromFirstCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        loginPage.validLogin(authInfo).validVerify(verificationCode);

        int amount = 100;
        var cardInfo = DataHelper.getFirstNumber();

        var dashboard = new DashboardPage();
        int balanceFirstCard = dashboard.getCardBalance("0");
        int balanceSecondCard = dashboard.getCardBalance("1");

        dashboard.selectCardToTransfer(1).makeTransfer(cardInfo, amount);
        int finalBalanceFirstCard = dashboard.getCardBalance("0");
        int finalBalanceSecondCard = dashboard.getCardBalance("1");

        assertTrue(finalBalanceFirstCard > 0 && finalBalanceSecondCard > 0);

        assertEquals((balanceFirstCard - amount), finalBalanceFirstCard);
        assertEquals((balanceSecondCard + amount), finalBalanceSecondCard);
    }

    @Test
    void shouldTransferCashFromSecondCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        loginPage.validLogin(authInfo).validVerify(verificationCode);

        int amount = 1000;
        var cardInfo = DataHelper.getSecondNumber();


        var dashboard = new DashboardPage();
        int finalBalanceFirstCard = dashboard.getCardBalance("1");
        int finalBalanceSecondCard = dashboard.getCardBalance("0");

        dashboard.selectCardToTransfer(0).makeTransfer(cardInfo, amount);
        int balanceFirstCard = dashboard.getCardBalance("1");
        int balanceSecondCard = dashboard.getCardBalance("0");

        assertTrue(finalBalanceFirstCard > 0 && finalBalanceSecondCard > 0);

        assertEquals(balanceFirstCard + amount, finalBalanceFirstCard);
        assertEquals(balanceSecondCard - amount, finalBalanceSecondCard);
    }

//    @Test
//    void shouldTransferNegativeBalance() {
//        open("http://localhost:9999");
//        var loginPage = new LoginPageV2();
//        var authInfo = DataHelper.getAuthInfo();
//        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//        loginPage.validLogin(authInfo).validVerify(verificationCode);
//
//        int amount = 20000;
//        var cardInfo = DataHelper.getFirstNumber();
//
//        var dashboard = new DashboardPage();
//
//        dashboard.selectCardToTransfer(1).makeTransfer(cardInfo, amount);
//        int finalBalanceFirstCard = dashboard.getCardBalance("0");
//        int finalBalanceSecondCard = dashboard.getCardBalance("1");
//
//        assertTrue(finalBalanceFirstCard > 0 && finalBalanceSecondCard > 0);
//    }


}

