package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }
    @Value
    public static class AuthInfoWrong {
        private String login;
        private String password;
    }

    public static AuthInfoWrong getAuthInfoWrong() {
        return new AuthInfoWrong("petya", "qwerty");
    }


    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        public String cardNumber;

    }

    public static CardInfo getFirstNumber() {
        return new CardInfo("5559000000000001");
    }

    public static CardInfo getSecondNumber() {
        return new CardInfo("5559000000000002");
    }

}
