package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    private SelenideElement errorMessage = $("[data-test-id=error-notification] .notification__content");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        verify(verificationCode);
        return new DashboardPage();
    }
    public VerificationPage verify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return this;
    }

    public void shouldErrorMessage() {
        errorMessage.shouldHave(exactText("Ошибка! Неверно указан код! Попробуйте ещё раз."));

    }
    public void shouldErrorMessageManyTry() {
        errorMessage.shouldHave(exactText("Ошибка! Превышено количество попыток ввода кода!"));

    }
}
