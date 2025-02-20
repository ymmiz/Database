package com.example.nutritrack.nutritrack_backend.model.converter;

import com.example.nutritrack.nutritrack_backend.model.constant.BaseEnum;
import jakarta.persistence.AttributeConverter;

public abstract class BaseEnumConverter<E extends Enum<E> & BaseEnum<V>, V> implements AttributeConverter<E, V> {

    private final Class<E> enumClass;

    protected BaseEnumConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public V convertToDatabaseColumn(E attribute) {
        return (attribute != null) ? attribute.getValue() : null; // ✅ Store as Integer
    }

    @Override
    public E convertToEntityAttribute(V dbData) {
        if (dbData == null) {
            return null;
        }

        // Use BaseEnum.fromValue() to handle conversion
        try {
            return BaseEnum.fromValue(enumClass, dbData);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown value: " + dbData + " for enum " + enumClass.getName());
        }
    }

}
