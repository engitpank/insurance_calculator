package ru.itpank.travel.insurance.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.dto.ValidationError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class TravelCalculatePremiumRequestValidatorTest {

    private final TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();

    private TravelCalculatePremiumRequest request;

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void validate_allFieldsValid_shouldReturnNoErrors() throws ParseException {
        request = createRequest("Ivan", "Ivanov",
                formatter.parse("2035-03-03"), formatter.parse("2035-03-08"));

        List<ValidationError> errors = validator.validate(request);

        Assertions.assertEquals(0, errors.size());
    }

    @Test
    void validate_firstNameIsNull_shouldReturnError() throws ParseException {
        request = createRequest("", "Ivanov",
                formatter.parse("2035-03-03"), formatter.parse("2035-03-08"));

        List<ValidationError> errors = validator.validate(request);

        ValidationError error = errors.getFirst();
        Assertions.assertEquals(error.getField(), "personFirstName");
        Assertions.assertEquals(error.getMessage(), "Must not be empty!");
    }

    @Test
    void validate_lastNameIsNull_shouldReturnError() throws ParseException {
        request = createRequest("Ivan", "",
                formatter.parse("2035-03-03"), formatter.parse("2035-03-08"));

        List<ValidationError> errors = validator.validate(request);

        ValidationError error = errors.getFirst();
        Assertions.assertEquals(error.getField(), "personLastName");
        Assertions.assertEquals(error.getMessage(), "Must not be empty!");
    }

    @Test
    void validate_dateFromIsNull_shouldReturnError() throws ParseException {
        request = createRequest("Ivan", "Ivanov",
                null, formatter.parse("2035-03-08"));

        List<ValidationError> errors = validator.validate(request);

        ValidationError error = errors.getFirst();
        Assertions.assertEquals(error.getField(), "agreementDateFrom");
        Assertions.assertEquals(error.getMessage(), "Must not be empty!");
    }

    @Test
    void validate_dateToIsNull_shouldReturnError() throws ParseException {
        request = createRequest("Ivan", "Ivanov",
                formatter.parse("2035-03-03"), null);

        List<ValidationError> errors = validator.validate(request);

        ValidationError error = errors.getFirst();
        Assertions.assertEquals(error.getField(), "agreementDateTo");
        Assertions.assertEquals(error.getMessage(), "Must not be empty!");
    }

    @Test
    void validate_dateToBeforeDateFrom_shouldReturnError() throws ParseException {
        request = createRequest("Ivan", "Ivanov",
                formatter.parse("2035-03-03"), formatter.parse("2035-03-01"));

        List<ValidationError> errors = validator.validate(request);

        ValidationError error = errors.getFirst();
        Assertions.assertEquals(error.getField(), "agreementDateTo");
        Assertions.assertEquals(error.getMessage(), "Must be after agreementDateFrom!");
    }

    @Test
    void validate_dateToEqualDateFrom_shouldReturnError() throws ParseException {
        request = createRequest("Ivan", "Ivanov",
                formatter.parse("2035-03-03"), formatter.parse("2035-03-03"));

        List<ValidationError> errors = validator.validate(request);

        ValidationError error = errors.getFirst();
        Assertions.assertEquals(error.getField(), "agreementDateTo");
        Assertions.assertEquals(error.getMessage(), "Must be after agreementDateFrom!");
    }

    @Test
    void validate_dateFromInPast_shouldReturnError() throws ParseException {
        request = createRequest("Ivan", "Ivanov",
                formatter.parse("2025-03-28"), formatter.parse("2035-03-03"));

        List<ValidationError> errors = validator.validate(request);

        ValidationError error = errors.getFirst();
        Assertions.assertEquals(error.getField(), "agreementDateFrom");
        Assertions.assertEquals(error.getMessage(), "The agreement date must not be in the past!");
    }


    @Test
    void validate_dateToInPast_shouldReturnError() throws ParseException {
        request = createRequest("Ivan", "Ivanov",
                formatter.parse("2025-03-27"), formatter.parse("2025-03-28"));

        List<ValidationError> errors = validator.validate(request);

        errors.stream()
                .filter(validationError -> validationError.getField().equals("agreementDateTo"))
                .filter(validationError -> validationError.getMessage().equals("The agreement date must not be in the past!"))
                .findFirst().orElseThrow();
    }


    private TravelCalculatePremiumRequest createRequest(String firstName, String lastName, Date from, Date to) {
        return new TravelCalculatePremiumRequest(firstName, lastName, from, to);
    }
}