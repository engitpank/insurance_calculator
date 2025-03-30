package ru.itpank.travel.insurance.core.validation.rules;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.dto.ValidationError;

import java.util.Date;
import java.util.Optional;

@Component
@Order(5)
class AgreementDateToBeAfterAgreementDateFromRule implements ValidationRule<TravelCalculatePremiumRequest> {

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();
        return dateFrom != null && dateTo != null
                && (dateTo.equals(dateFrom) || dateFrom.after(dateTo))
                ? Optional.of(new ValidationError("agreementDateTo", "Must be after agreementDateFrom!"))
                : Optional.empty();
    }
}