package ru.itpank.travel.insurance.core.validation.rules;

import ru.itpank.travel.insurance.dto.ValidationError;

import java.util.Optional;

public interface ValidationRule<T> {
    Optional<ValidationError> validate(T request);
}