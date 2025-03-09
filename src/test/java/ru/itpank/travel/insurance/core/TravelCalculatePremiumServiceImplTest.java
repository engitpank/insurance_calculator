package ru.itpank.travel.insurance.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itpank.travel.insurance.rest.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class TravelCalculatePremiumServiceImplTest {

    TravelCalculatePremiumService travelCalculatePremiumService;
    TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() throws ParseException {
        travelCalculatePremiumService = new TravelCalculatePremiumServiceImpl();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date agreementDateFrom = formatter.parse("2025-03-03");
        Date agreementDateTo = formatter.parse("2025-03-08");
        request = new TravelCalculatePremiumRequest(
                "Ivan",
                "Ivanov",
                agreementDateFrom,
                agreementDateTo
        );
    }

    @Test
    public void calculatePremium_ValidFirstNameInRequest_ReturnsResponseWithCopiedFirstName() {
        String expectedFirstName = request.getPersonFirstName();

        TravelCalculatePremiumResponse actualResponse = travelCalculatePremiumService.calculatePremium(request);

        Assertions.assertEquals(expectedFirstName, actualResponse.getPersonFirstName());
    }

    @Test
    public void calculatePremium_ValidLastNameInRequest_ReturnsResponseWithCopiedFirstName() {
        String expectedLastName = request.getPersonLastName();

        TravelCalculatePremiumResponse actualResponse = travelCalculatePremiumService.calculatePremium(request);

        Assertions.assertEquals(expectedLastName, actualResponse.getPersonLastName());
    }

    @Test
    public void calculatePremium_ValidAgreementDateFromInRequest_ReturnsResponseWithAgreementDateFrom() {
        Date expectedAgreementDateFrom = request.getAgreementDateFrom();

        TravelCalculatePremiumResponse actualResponse = travelCalculatePremiumService.calculatePremium(request);

        Assertions.assertEquals(expectedAgreementDateFrom, actualResponse.getAgreementDateFrom());
    }

    @Test
    public void calculatePremium_ValidAgreementDateToInRequest_ReturnsResponseWithAgreementDateTo() {
        Date expectedAgreementDateTo = request.getAgreementDateTo();

        TravelCalculatePremiumResponse actualResponse = travelCalculatePremiumService.calculatePremium(request);

        Assertions.assertEquals(expectedAgreementDateTo, actualResponse.getAgreementDateTo());
    }

}