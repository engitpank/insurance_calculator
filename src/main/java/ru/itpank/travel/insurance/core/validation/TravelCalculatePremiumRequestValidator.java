package ru.itpank.travel.insurance.core.validation;

import ru.itpank.travel.insurance.dto.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.dto.ValidationError;

import java.util.List;

public interface TravelCalculatePremiumRequestValidator {
    List<ValidationError> validate(TravelCalculatePremiumRequest request);
}