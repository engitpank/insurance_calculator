package ru.itpank.travel.insurance.core.validation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itpank.travel.insurance.core.validation.rules.ValidationRule;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.dto.ValidationError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator {
    private List<ValidationRule<TravelCalculatePremiumRequest>> validationRules;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        if (request == null) {
            errors.add(new ValidationError("request", "Request must not be null!"));
            return errors;
        }
        validationRules
                .stream()
                .map(validator -> validator.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(errors::add);
        return errors;
    }
}