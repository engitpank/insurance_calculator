package ru.itpank.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
class DateTimeService {

    long calculateDaysBetween(Date from, Date to) {
        return Duration
                .between(from.toInstant(), to.toInstant())
                .toDays();
    }
}