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

public class TravelCalculatePremiumServiceImplAIEachFieldTest {
    @Mock
    private TravelCalculatePremiumRequest requestMock;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculatePremium_PersonFirstName() {
        // Arrange
        String firstName = "Alice";
        when(requestMock.getPersonFirstName()).thenReturn(firstName);

        // Act
        TravelCalculatePremiumResponse response = service.calculatePremium(requestMock);
        System.out.println(response);
        // Assert
        assertEquals(firstName, response.getPersonFirstName());
    }

    @Test
    public void testCalculatePremium_PersonLastName() {
        // Arrange
        String lastName = "Smith";
        when(requestMock.getPersonLastName()).thenReturn(lastName);

        // Act
        TravelCalculatePremiumResponse response = service.calculatePremium(requestMock);

        // Assert
        assertEquals(lastName, response.getPersonLastName());
    }

    @Test
    public void testCalculatePremium_AgreementDateFrom() {
        // Arrange
        Date dateFrom = createDate(2023, Calendar.JUNE, 15);
        when(requestMock.getAgreementDateFrom()).thenReturn(dateFrom);

        // Act
        TravelCalculatePremiumResponse response = service.calculatePremium(requestMock);

        // Assert
        assertEquals(dateFrom, response.getAgreementDateFrom());
    }

    @Test
    public void testCalculatePremium_AgreementDateTo() {
        // Arrange
        Date dateTo = createDate(2025, Calendar.JANUARY, 1);
        when(requestMock.getAgreementDateTo()).thenReturn(dateTo);

        // Act
        TravelCalculatePremiumResponse response = service.calculatePremium(requestMock);

        // Assert
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
