package com.demo.elearn.models.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;


@Converter(autoApply = true)
public class PriceComponentConverter implements AttributeConverter<PriceComponentType, String> {
    @Override
    public String convertToDatabaseColumn(PriceComponentType attribute) {
        if(attribute == null) {
            return null;
        }
        return attribute.getDescription();
    }

    @Override
    public PriceComponentType convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }

        return Stream.of(PriceComponentType.values())
                .filter(c -> c.getDescription().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
