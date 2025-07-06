package com.fmattaperdomo.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class Address {
    private final UUID id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;

    public Address(UUID id, String street, String city, String state, String country, String zipcode) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
    }

    public UUID getId() {
        return id;
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

    public String getCountry() {
        return country;
    }

    public String getZipcode() {
        return zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address that = (Address) o;
        return street.equals(that.street) && city.equals(that.city) && state.equals(that.state) && country.equals(that.country) && zipcode.equals(that.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, state, country, zipcode);
    }
}
