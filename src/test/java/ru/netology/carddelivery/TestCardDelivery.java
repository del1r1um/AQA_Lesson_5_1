package ru.netology.carddelivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;
import ru.netology.data.PersonalDeliveryData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.data.DataGenerator.DeliveryData.*;

class TestCardDelivery {
    PersonalDeliveryData data = DataGenerator.DeliveryData.generate();

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldRescheduleMeetingDate() {
        $("[data-test-id='city'] .input__control").setValue(validCity());
        $("[data-test-id='date'] .input__control").doubleClick()
                .sendKeys(Keys.DELETE, generateDate(7));
        $("[data-test-id='name'] .input__control").setValue(data.getName());
        $("[data-test-id='phone'] .input__control").setValue(data.getPhoneNumber());
        $(".checkbox__box").click();
        $(".button__text").click();
        $(".notification__content").shouldHave(text("Встреча успешно запланирована на "
                + generateDate(7)));
        $("[data-test-id='date'] .input__control").doubleClick()
                .sendKeys(Keys.DELETE, generateDate(10));
        $(".button__text").click();
        $("[class='notification__content'] .button__text").click();
        $(".notification__content").shouldHave(text("Встреча успешно запланирована на "
                + generateDate(10)));
    }

    @Test
    void shouldSendFormWithEmptyCityField() {
        $("[data-test-id='date'] .input__control").doubleClick()
                .sendKeys(Keys.DELETE, generateDate(7));
        $("[data-test-id='name'] .input__control").setValue(data.getName());
        $("[data-test-id='phone'] .input__control").setValue(data.getPhoneNumber());
        $(".checkbox__box").click();
        $(".button__text").click();
        $("[data-test-id='city'].input_invalid").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSendFormWithNotCyrillicName() {
        $("[data-test-id='city'] .input__control").setValue(validCity());
        $("[data-test-id='date'] .input__control").doubleClick()
                .sendKeys(Keys.DELETE, generateDate(7));
        $("[data-test-id='name'] .input__control").setValue("Ivan Ivanov");
        $("[data-test-id='phone'] .input__control").setValue(data.getPhoneNumber());
        $(".checkbox__box").click();
        $(".button__text").click();
        $("[data-test-id='name'].input_invalid")
                .shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSendFormWithEmptyNameField() {
        $("[data-test-id='city'] .input__control").setValue(validCity());
        $("[data-test-id='date'] .input__control").doubleClick()
                .sendKeys(Keys.DELETE, generateDate(7));
        $("[data-test-id='phone'] .input__control").setValue(data.getPhoneNumber());
        $(".checkbox__box").click();
        $(".button__text").click();
        $("[data-test-id='name'].input_invalid").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSendFormWithEmptyPhoneField() {
        $("[data-test-id='city'] .input__control").setValue(validCity());
        $("[data-test-id='date'] .input__control").doubleClick()
                .sendKeys(Keys.DELETE, generateDate(7));
        $("[data-test-id='name'] .input__control").setValue(data.getName());
        $(".checkbox__box").click();
        $(".button__text").click();
        $("[data-test-id='phone'].input_invalid").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSendFormWithEmptyDateField() {
        $("[data-test-id='city'] .input__control").setValue(validCity());
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='name'] .input__control").setValue(data.getName());
        $("[data-test-id='phone'] .input__control").setValue(data.getPhoneNumber());
        $(".checkbox__box").click();
        $(".button__text").click();
        $("[data-test-id='date'] .input_invalid").shouldHave(text("Неверно введена дата"));
    }

    @Test
    void shouldSendFormWithNotCheckedAgreement() {
        $("[data-test-id='city'] .input__control").setValue(validCity());
        $("[data-test-id='date'] .input__control").doubleClick()
                .sendKeys(Keys.DELETE, generateDate(7));
        $("[data-test-id='name'] .input__control").setValue(data.getName());
        $("[data-test-id='phone'] .input__control").setValue(data.getPhoneNumber());
        $(".button__text").click();
        $("[data-test-id='agreement'].input_invalid")
                .shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void shouldSendFormWithInvalidCity() {
        $("[data-test-id='city'] .input__control").setValue(invalidCity());
        $("[data-test-id='date'] .input__control").doubleClick()
                .sendKeys(Keys.DELETE, generateDate(7));
        $("[data-test-id='name'] .input__control").setValue(data.getName());
        $("[data-test-id='phone'] .input__control").setValue(data.getPhoneNumber());
        $(".checkbox__box").click();
        $(".button__text").click();
        $("[data-test-id='city'] .input__sub").shouldHave(text("Доставка в выбранный город недоступна"));
    }
}
