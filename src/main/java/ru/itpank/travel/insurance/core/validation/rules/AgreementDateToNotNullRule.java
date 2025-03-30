package ru.itpank.travel.insurance.core.validation.rules;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.dto.ValidationError;

import java.util.Optional;

@Component
@Order(4)
class AgreementDateToNotNullRule implements ValidationRule<TravelCalculatePremiumRequest> {

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be empty!"))
                : Optional.empty();
    }
}