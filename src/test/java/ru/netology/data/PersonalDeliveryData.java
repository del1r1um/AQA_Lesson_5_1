package ru.netology.data;

public class PersonalDeliveryData {
    private final String city;
    private final String name;
    private final String phoneNumber;

    public PersonalDeliveryData(String city, String name, String phoneNumber) {
        this.city = city;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}