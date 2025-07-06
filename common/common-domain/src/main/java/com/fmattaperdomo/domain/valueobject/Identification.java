package com.fmattaperdomo.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class Identification {
    private final UUID id;
    private TypeIdentification typeIdentification;
    private String documentNumber;

    public Identification(UUID id, TypeIdentification typeIdentification, String documentNumber) {
        this.id = id;
        this.typeIdentification = typeIdentification;
        this.documentNumber = documentNumber;
    }

    public UUID getId() {
        return id;
    }

    public TypeIdentification getTypeIdentification() {
        return typeIdentification;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identification that = (Identification) o;
        return typeIdentification.equals(that.typeIdentification) && documentNumber.equals(that.documentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeIdentification, documentNumber);
    }
}
