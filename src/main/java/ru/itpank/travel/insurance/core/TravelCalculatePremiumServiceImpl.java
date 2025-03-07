package ru.itpank.travel.insurance.core;

import org.springframework.stereotype.Component;
import ru.itpank.travel.insurance.rest.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.rest.TravelCalculatePremiumResponse;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {


    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        return new TravelCalculatePremiumResponse();
    }

}
