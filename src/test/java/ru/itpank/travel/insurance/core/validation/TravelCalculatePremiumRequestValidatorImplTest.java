package ru.itpank.travel.insurance.core.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itpank.travel.insurance.core.validation.rules.ValidationRule;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.dto.ValidationError;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorImplTest {
    private List<ValidationRule<TravelCalculatePremiumRequest>> rules;
    private TravelCalculatePremiumRequestValidatorImpl validator;
    @Mock
    private ValidationRule<TravelCalculatePremiumRequest> passRule;
    @Mock
    private ValidationRule<TravelCalculatePremiumRequest> failRule1;
    @Mock
    private ValidationRule<TravelCalculatePremiumRequest> failRule2;
    @Mock
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        rules = new LinkedList<>();
        validator = new TravelCalculatePremiumRequestValidatorImpl(rules);
    }

    @Test
    void requestPassAllRules_shouldReturnEmptyList() {
        Mockito.when(passRule.validate(request)).thenReturn(Optional.empty());
        rules.add(passRule);
        List<ValidationError> result = validator.validate(request);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void requestFailedOneRule_shouldReturnOneError() {
        Mockito.when(failRule1.validate(request)).thenReturn(Optional.of(new ValidationError("field1", "invalid")));
        rules.add(failRule1);
        List<ValidationError> result = validator.validate(request);
        Assertions.assertEquals(1, result.size());
        Assertions.assertTrue(result.getFirst().getField().contains("field1"));
        Assertions.assertTrue(result.getFirst().getMessage().contains("invalid"));
    }

    @Test
    void requestFailedRules_shouldReturnErrors() {
        Mockito.when(failRule1.validate(request)).thenReturn(Optional.of(new ValidationError("field1", "invalid")));
        Mockito.when(failRule2.validate(request)).thenReturn(Optional.of(new ValidationError("field2", "invalid")));
        rules.add(failRule1);
        rules.add(failRule2);
        List<ValidationError> result = validator.validate(request);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("field1", result.getFirst().getField());
        Assertions.assertEquals("field2", result.getLast().getField());
    }
}