package com.fmattaperdomo.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class Contact {
    private final UUID id;
    private String name;
    private String phone;

    public Contact(UUID id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact that = (Contact) o;
        return name.equals(that.name) && phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }
}
