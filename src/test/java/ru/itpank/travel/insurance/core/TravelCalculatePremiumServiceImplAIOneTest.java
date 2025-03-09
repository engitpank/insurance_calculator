package ru.itpank.travel.insurance.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.itpank.travel.insurance.rest.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumServiceImplAIOneTest {
    @Mock
    private TravelCalculatePremiumRequest requestMock;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculatePremium_AllProperties() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        Date dateFrom = createDate(2024, Calendar.JANUARY, 1);
        Date dateTo = createDate(2024, Calendar.DECEMBER, 31);

        when(requestMock.getPersonFirstName()).thenReturn(firstName);
        when(requestMock.getPersonLastName()).thenReturn(lastName);
        when(requestMock.getAgreementDateFrom()).thenReturn(dateFrom);
        when(requestMock.getAgreementDateTo()).thenReturn(dateTo);

        // Act
        TravelCalculatePremiumResponse response = service.calculatePremium(requestMock);

        // Assert
        assertEquals(firstName, response.getPersonFirstName());
        assertEquals(lastName, response.getPersonLastName());
        assertEquals(dateFrom, response.getAgreementDateFrom());
        assertEquals(dateTo, response.getAgreementDateTo());
    }

    // Вспомогательный метод для создания дат
    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
