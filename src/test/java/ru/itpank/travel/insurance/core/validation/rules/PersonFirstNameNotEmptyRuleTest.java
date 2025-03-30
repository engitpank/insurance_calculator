package ru.itpank.travel.insurance.core.validation.rules;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.dto.ValidationError;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PersonFirstNameNotEmptyRuleTest {
    private final PersonFirstNameNotEmptyRule rule = new PersonFirstNameNotEmptyRule();
    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    void firstNameNotNull_shouldReturnEmpty() {
        Mockito.when(request.getPersonFirstName()).thenReturn("Ivan");
        Optional<ValidationError> result = rule.validate(request);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void firstNameNull_shouldReturnError() {
        Mockito.when(request.getPersonFirstName()).thenReturn(null);
        Optional<ValidationError> result = rule.validate(request);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void firstNameBlank_shouldReturnError() {
        Mockito.when(request.getPersonFirstName()).thenReturn(" ");
        Optional<ValidationError> result = rule.validate(request);
        Assertions.assertTrue(result.isPresent());
    }
}