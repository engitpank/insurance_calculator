package ru.itpank.travel.insurance.core;


import ru.itpank.travel.insurance.rest.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.rest.TravelCalculatePremiumResponse;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request);

}
