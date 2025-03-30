package ru.itpank.travel.insurance.core.validation.rules;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itpank.travel.insurance.core.DateTimeService;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.dto.ValidationError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AgreementDateFromInPresentRuleTest {
    @Mock
    private DateTimeService dateTimeService;
    @InjectMocks
    private AgreementDateFromInPresentRule rule;
    @Mock
    private TravelCalculatePremiumRequest request;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void dateToInPresent_shouldReturnEmpty() throws ParseException {
        Mockito.when(dateTimeService.getCurrentDateWithoutTime()).thenReturn(dateFormat.parse("2025-03-25"));
        Mockito.when(request.getAgreementDateFrom()).thenReturn(dateFormat.parse("2025-03-26"));
        Optional<ValidationError> result = rule.validate(request);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void dateToInToday_shouldReturnEmpty() throws ParseException {
        Mockito.when(dateTimeService.getCurrentDateWithoutTime()).thenReturn(dateFormat.parse("2025-03-25"));
        Mockito.when(request.getAgreementDateFrom()).thenReturn(dateFormat.parse("2025-03-25"));
        Optional<ValidationError> result = rule.validate(request);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void dateToInPast_shouldReturnError() throws ParseException {
        Mockito.when(dateTimeService.getCurrentDateWithoutTime()).thenReturn(dateFormat.parse("2025-03-25"));
        Mockito.when(request.getAgreementDateFrom()).thenReturn(dateFormat.parse("2025-03-24"));
        Optional<ValidationError> result = rule.validate(request);
        Assertions.assertTrue(result.isPresent());
    }
}