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
class PersonLastNameNotEmptyRuleTest {
    private final PersonLastNameNotEmptyRule rule = new PersonLastNameNotEmptyRule();
    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    void lastNameNotNull_shouldReturnEmpty() {
        Mockito.when(request.getPersonLastName()).thenReturn("Ivanov");
        Optional<ValidationError> result = rule.validate(request);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void lastNameNull_shouldReturnError() {
        Mockito.when(request.getPersonLastName()).thenReturn(null);
        Optional<ValidationError> result = rule.validate(request);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void lastNameBlank_shouldReturnError() {
        Mockito.when(request.getPersonLastName()).thenReturn(" ");
        Optional<ValidationError> result = rule.validate(request);
        Assertions.assertTrue(result.isPresent());
    }
}