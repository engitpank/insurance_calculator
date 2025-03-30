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
class AgreementDateFromNotNullRuleTest {
    private final AgreementDateFromNotNullRule rule = new AgreementDateFromNotNullRule();
    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    void dateFromNotNull_shouldReturnEmpty() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Mockito.when(request.getAgreementDateFrom()).thenReturn(dateFormat.parse("2025-03-29"));
        Optional<ValidationError> result = rule.validate(request);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void dateFromNull_shouldReturnError() {
        Mockito.when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> result = rule.validate(request);
        Assertions.assertTrue(result.isPresent());
    }
}