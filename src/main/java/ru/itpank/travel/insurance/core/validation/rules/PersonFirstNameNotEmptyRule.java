package ru.itpank.travel.insurance.core.validation.rules;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.dto.ValidationError;

import java.util.Optional;

@Component
@Order(1)
class PersonFirstNameNotEmptyRule implements ValidationRule<TravelCalculatePremiumRequest> {

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isBlank())
                ? Optional.of(new ValidationError("personFirstName", "Must not be empty!"))
                : Optional.empty();
    }
}