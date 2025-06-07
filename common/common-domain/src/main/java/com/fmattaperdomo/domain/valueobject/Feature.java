package com.fmattaperdomo.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class Feature {
    private final Long id;
    private String name;

    public Feature(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
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
