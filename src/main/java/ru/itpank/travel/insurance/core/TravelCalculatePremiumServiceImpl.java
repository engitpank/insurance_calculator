package ru.itpank.travel.insurance.core;

import org.springframework.stereotype.Component;
import ru.itpank.travel.insurance.rest.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {


    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        long daysBetween = calculateDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());

        return new TravelCalculatePremiumResponse(request.getPersonFirstName(), request.getPersonLastName(),
                request.getAgreementDateFrom(), request.getAgreementDateTo(), new BigDecimal(daysBetween));
    }

    private static long calculateDaysBetween(Date from, Date to) {
        return Duration
                .between(from.toInstant(), to.toInstant())
                .toDays();
    }

}
