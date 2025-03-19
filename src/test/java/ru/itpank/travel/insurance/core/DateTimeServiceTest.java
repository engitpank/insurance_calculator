package ru.itpank.travel.insurance.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class DateTimeServiceTest {

    private final DateTimeService dts = new DateTimeService();
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void calculateDaysBetween_shouldBePositive() throws ParseException {
        Date date1 = formatter.parse("2025-03-10");
        Date date2 = formatter.parse("2025-03-19");

        Assertions.assertEquals(9, dts.calculateDaysBetween(date1, date2));
    }

    @Test
    public void calculateDaysBetween_shouldBeNegative() throws ParseException {
        Date date1 = formatter.parse("2025-03-19");
        Date date2 = formatter.parse("2025-03-10");

        Assertions.assertEquals(-9, dts.calculateDaysBetween(date1, date2));
    }

    @Test
    public void calculateDaysBetween_shouldBeZero() throws ParseException {
        Date date1 = formatter.parse("2025-03-10");
        Date date2 = formatter.parse("2025-03-10");

        Assertions.assertEquals(0, dts.calculateDaysBetween(date1, date2));
    }
}