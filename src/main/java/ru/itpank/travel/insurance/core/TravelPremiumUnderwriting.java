package ru.itpank.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelPremiumUnderwriting {

    private final DateTimeService dateTimeService;

    BigDecimal calculatePremium(TravelCalculatePremiumRequest request){
        long daysBetween = dateTimeService.calculateDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());
        return BigDecimal.valueOf(daysBetween);
    }
}
