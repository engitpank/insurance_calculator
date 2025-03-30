package ru.itpank.travel.insurance.core.validation.rules;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.dto.ValidationError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AgreementDateToBeAfterAgreementDateFromRuleTest {
    private final AgreementDateToBeAfterAgreementDateFromRule rule = new AgreementDateToBeAfterAgreementDateFromRule();
    @Mock
    private TravelCalculatePremiumRequest request;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void agreementDateToAfterDateFrom_shouldReturnEmpty() throws ParseException {
        Mockito.when(request.getAgreementDateFrom()).thenReturn(dateFormat.parse("2025-03-02"));
        Mockito.when(request.getAgreementDateTo()).thenReturn(dateFormat.parse("2025-03-03"));
        Optional<ValidationError> result = rule.validate(request);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void agreementDateToEqualsDateFrom_shouldReturnEmpty() throws ParseException {
        Mockito.when(request.getAgreementDateFrom()).thenReturn(dateFormat.parse("2025-03-02"));
        Mockito.when(request.getAgreementDateTo()).thenReturn(dateFormat.parse("2025-03-02"));
        Optional<ValidationError> result = rule.validate(request);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void agreementDateToBeforeDateFrom_shouldReturnEmpty() throws ParseException {
        Mockito.when(request.getAgreementDateFrom()).thenReturn(dateFormat.parse("2025-03-02"));
        Mockito.when(request.getAgreementDateTo()).thenReturn(dateFormat.parse("2025-03-01"));
        Optional<ValidationError> result = rule.validate(request);
        Assertions.assertTrue(result.isPresent());
    }
}