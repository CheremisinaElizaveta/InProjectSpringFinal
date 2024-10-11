package com.example.InProject.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<Date, String> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String convertToDatabaseColumn(Date date) {
        return dateFormat.format(date);
    }

    @Override
    public Date convertToEntityAttribute(String dbData) {
        try {
            return dateFormat.parse(dbData);
        } catch (Exception e) {
            throw new IllegalArgumentException("Неверный формат даты", e);
        }
    }
}