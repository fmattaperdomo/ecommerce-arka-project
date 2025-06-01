package com.fmattaperdomo.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class Feature {
    private final UUID id;
    private String name;

    public Feature(UUID id, String name, String phone) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature that = (Feature) o;
        return name.equals(that.name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
