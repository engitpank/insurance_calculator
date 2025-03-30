package ru.itpank.travel.insurance.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@ExtendWith(MockitoExtension.class)
class TravelPremiumUnderwritingTest {
    @Mock
    DateTimeService dateTimeService;
    @InjectMocks
    TravelPremiumUnderwriting travelPremiumUnderwriting;
    TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        request = new TravelCalculatePremiumRequest(
                "Ivan",
                "Ivanov",
                formatter.parse("2025-03-03"),
                formatter.parse("2025-03-08")
        );
    }

    @Test
    void calculatePremium_CorrectOrderPassDatesToDateTimeService() {
        travelPremiumUnderwriting.calculatePremium(request);
        Mockito.verify(dateTimeService).calculateDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}