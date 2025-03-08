package ru.itpank.travel.insurance.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.itpank.travel.insurance.rest.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.util.Date;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void calculatePremium_ValidRequest_ReturnsResponseWithCopiedFields() {
        // Arrange
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Ivan",
                "Ivanov",
                new Date(),
                new Date()
        );
        TravelCalculatePremiumService travelCalculatePremiumService = new TravelCalculatePremiumServiceImpl();

        // Act
        TravelCalculatePremiumResponse actualResponse = travelCalculatePremiumService.calculatePremium(request);

        // Assert
        Assertions.assertEquals(request.getPersonFirstName(), actualResponse.getPersonFirstName());
        Assertions.assertEquals(request.getPersonFirstName(), actualResponse.getPersonFirstName());
        Assertions.assertEquals(request.getAgreementDateFrom(), actualResponse.getAgreementDateFrom());
        Assertions.assertEquals(request.getAgreementDateTo(), actualResponse.getAgreementDateTo());
    }

}