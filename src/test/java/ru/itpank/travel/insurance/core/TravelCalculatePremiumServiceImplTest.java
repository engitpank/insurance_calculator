package ru.itpank.travel.insurance.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TravelCalculatePremiumServiceImplTest {
    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl travelCalculatePremiumService;

    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date agreementDateFrom = formatter.parse("2025-03-03");
        Date agreementDateTo = formatter.parse("2025-03-08");
        request = new TravelCalculatePremiumRequest(
                "Ivan",
                "Ivanov",
                agreementDateFrom,
                agreementDateTo
        );
        long daysBetween = 5;
        Mockito.when(dateTimeService.calculateDaysBetween(agreementDateFrom, agreementDateTo)).thenReturn(daysBetween);
        Mockito.when(requestValidator.validate(any())).thenReturn(List.of());
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