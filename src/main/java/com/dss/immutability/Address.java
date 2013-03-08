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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!city.equals(address.city)) return false;
        if (!homePhone.equals(address.homePhone)) return false;
        if (!mobilePhone.equals(address.mobilePhone)) return false;
        if (!state.equals(address.state)) return false;
        if (!street.equals(address.street)) return false;
        if (!streetNumber.equals(address.streetNumber)) return false;
        if (!zip.equals(address.zip)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = streetNumber.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + zip.hashCode();
        result = 31 * result + homePhone.hashCode();
        result = 31 * result + mobilePhone.hashCode();
        return result;
    }
}
