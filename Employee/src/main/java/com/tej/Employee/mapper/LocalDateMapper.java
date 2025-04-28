package com.tej.Employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper
public interface LocalDateMapper {

    // Static instance of LocalDateMapper to use in other mappers
    LocalDateMapper INSTANCE = Mappers.getMapper(LocalDateMapper.class);

    // Convert LocalDate to String
    default String map(LocalDate date) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    // Convert String to LocalDate
    default LocalDate map(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }
}
