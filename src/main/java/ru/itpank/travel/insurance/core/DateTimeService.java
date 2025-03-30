package ru.itpank.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateTimeService {

    long calculateDaysBetween(Date from, Date to) {
        return Duration
                .between(from.toInstant(), to.toInstant())
                .toDays();
    }

    public Date getCurrentDateWithoutTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}