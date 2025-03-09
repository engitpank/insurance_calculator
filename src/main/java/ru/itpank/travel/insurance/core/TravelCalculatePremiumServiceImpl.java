package ru.itpank.travel.insurance.core;

import org.springframework.stereotype.Component;
import ru.itpank.travel.insurance.rest.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.math.BigDecimal;
import java.time.Duration;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {


    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        long daysBetween = Duration
                .between(request.getAgreementDateFrom().toInstant(), request.getAgreementDateTo().toInstant())
                .toDays();

        return new TravelCalculatePremiumResponse(request.getPersonFirstName(), request.getPersonLastName(),
                request.getAgreementDateFrom(), request.getAgreementDateTo(), new BigDecimal(daysBetween));
    }

}
