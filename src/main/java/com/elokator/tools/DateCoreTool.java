package com.elokator.tools;

import com.elokator.enums.CountryEnum;
import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.errors.ApiError;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class DateCoreTool {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public DateCoreTool() {
    }

    public LocalDate addDaysToDate(LocalDate date, int days) {
        return date.plusDays(days);
    }

    public LocalDateTime addDaysToDateTime(LocalDateTime dateTime, int days) {
        return dateTime.plusDays(days);
    }

    public String formatLocalDateTime(final LocalDateTime dateTime) throws AppCoreException {
        try {
            return dateTime.format(DATE_TIME_FORMATTER);
        } catch (DateTimeException e) {
            throw new AppCoreException(ApiError.INVALID_DATE_FORMAT, "Can not format local date time yyyy-MM-dd HH:mm:ss");
        }
    }

    public String formatLocalDate(final LocalDate date) throws AppCoreException {
        try {
            return date.format(DATE_FORMATTER);
        } catch (DateTimeException e) {
            throw new AppCoreException(ApiError.INVALID_DATE_FORMAT, "Can not format local date yyyy-MM-dd");
        }
    }

    public void validateDateFormat(String date) throws AppCoreException {
        try {
            DATE_FORMATTER.parse(date);
        } catch (DateTimeParseException e) {
            throw new AppCoreException(ApiError.INVALID_DATE_FORMAT, "Expected format is yyyy-MM-dd");
        }
    }

    public void validateDateTimeFormat(String dateTime) throws AppCoreException {
        try {
            DATE_TIME_FORMATTER.parse(dateTime);
        } catch (DateTimeParseException e) {
            throw new AppCoreException(ApiError.INVALID_DATE_FORMAT, "Expected format is yyyy-MM-dd HH:mm:ss");
        }
    }

    public LocalDateTime parseDateWithStartOfDay(final String date) throws AppCoreException {
        try {
            return LocalDate.parse(date, DATE_FORMATTER).atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new AppCoreException(ApiError.INVALID_DATE_FORMAT, "Can not parse with start of day");
        }
    }

    public LocalDateTime parseDateWithEndOfDay(final String date) throws AppCoreException {
        try {
            return LocalDate.parse(date, DATE_FORMATTER)
                    .atTime(23, 59, 59);
        } catch (DateTimeParseException e) {
            throw new AppCoreException(ApiError.INVALID_DATE_FORMAT, "Can not parse with end of day");
        }
    }

    public LocalDateTime parseDateWithStartOfDay(final LocalDate date) throws AppCoreException {
        try {
            return date.atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new AppCoreException(ApiError.INVALID_DATE_FORMAT, "Can not parse with start of day");
        }
    }

    public LocalDateTime parseDateWithEndOfDay(final LocalDate date) throws AppCoreException {
        try {
            return date.atTime(23, 59, 59);
        } catch (DateTimeParseException e) {
            throw new AppCoreException(ApiError.INVALID_DATE_FORMAT, "Can not parse with end of day");
        }
    }

    public boolean isAdult(LocalDate birthDate, String countryCode) {
        int legalAge = 18;
        if (CountryEnum.ESTONIA.getAlpha2Code().equals(countryCode)) {
            legalAge = 21;
        }
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);
        return age.getYears() >= legalAge;
    }
}
