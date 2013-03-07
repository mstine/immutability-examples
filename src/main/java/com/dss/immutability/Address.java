package com.dss.immutability;

public class Address {

    private final String streetNumber;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private final PhoneNumber homePhone;
    private final PhoneNumber mobilePhone;

    public Address(String streetNumber, String street, String city, String state, String zip, PhoneNumber homePhone, PhoneNumber mobilePhone) {
        this.streetNumber = streetNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public PhoneNumber getHomePhone() {
        return homePhone;
    }

    public PhoneNumber getMobilePhone() {
        return mobilePhone;
    }
}
