package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class DeliveryData {
        private DeliveryData() {
        }

        public static PersonalDeliveryData generate() {
            Faker faker = new Faker(new Locale("ru"));
            return new PersonalDeliveryData(
                    faker.address().city(),
                    faker.name().firstName().replaceAll("[^А-ЕЖ-Яа-еж-я]", "е") + " "
                            + faker.name().lastName().replaceAll("[^А-ЕЖ-Яа-еж-я]", "е"),
                    faker.phoneNumber().phoneNumber());
        }

        public static String generateDate(int days) {
            return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String validCity() {
            Random random = new Random();
            int rand = random.nextInt(10);
            String[] validCity = {"Москва", "Санкт-Петербург", "Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ",
                    "Калининград", "Салехард", "Тверь", "Екатеринбург"};
            return validCity[rand];
        }

        public static String invalidCity() {
            Random random = new Random();
            int rand = random.nextInt(10);
            String[] invalidCity = {"Химки", "Клин", "Солнечногорск", "Зеленоград", "Люберцы", "Балашиха",
                    "Долгопрудный", "Одинцово", "Королев", "Мытищи"};
            return invalidCity[rand];
        }
    }
}