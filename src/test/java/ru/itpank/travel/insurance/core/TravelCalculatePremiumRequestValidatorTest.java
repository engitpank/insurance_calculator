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
    void validate_AllFieldsValid_shouldReturnNoErrors() throws ParseException {
        request = createRequest("Ivan", "Ivanov",
                formatter.parse("2025-03-03"), formatter.parse("2025-03-08"));

        List<ValidationError> errors = validator.validate(request);

        Assertions.assertEquals(0, errors.size());
    }

    @Test
    void validate_FirstNameInvalid_shouldReturnError() throws ParseException {
        request = createRequest("", "Ivanov",
                formatter.parse("2025-03-03"), formatter.parse("2025-03-08"));

        List<ValidationError> errors = validator.validate(request);

        Assertions.assertEquals(1, errors.size());
        ValidationError error = errors.getFirst();
        Assertions.assertEquals(error.getField(), "personFirstName");
        Assertions.assertEquals(error.getMessage(), "Must not be empty!");
    }

    @Test
    void validate_AllFieldsInvalid_shouldReturnNoErrors() {
        request = createRequest("", "", null, null);

        List<ValidationError> errors = validator.validate(request);

        Assertions.assertEquals(4, errors.size());
    }


    private TravelCalculatePremiumRequest createRequest(String firstName, String lastName, Date from, Date to) {
        return new TravelCalculatePremiumRequest(firstName, lastName, from, to);
    }
}