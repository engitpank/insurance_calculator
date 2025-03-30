package ru.itpank.travel.insurance.core.validation.rules;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.itpank.travel.insurance.core.DateTimeService;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.dto.ValidationError;

import java.util.Date;
import java.util.Optional;

@Component
@Order(7)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateToInPresentRule implements ValidationRule<TravelCalculatePremiumRequest> {
    private DateTimeService dateTimeService;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateTo();
        Date present = dateTimeService.getCurrentDateWithoutTime();
        return (dateFrom != null && (dateFrom.before(present) && !dateFrom.equals(present)))
                ? Optional.of(new ValidationError("agreementDateTo", "The agreement date must not be in the past!"))
                : Optional.empty();
    }
}