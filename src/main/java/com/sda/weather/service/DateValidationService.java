package com.sda.weather.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class DateValidationService {

    public static LocalDate validate(String clientDate) {
        if (clientDate.isBlank() || clientDate == null) {
            return LocalDate.now().plusDays(1);
        }
        if (clientDate.length() != 10) {
            throw new RuntimeException("Illegal date format.");
        }
        String[] splitDate = clientDate.split("-");
        if (splitDate[0].length() != 4) {
            throw new RuntimeException("Illegal date format.");
        }
        if (splitDate[1].length() != 2) {
            throw new RuntimeException("Illegal date format.");
        }
        if (splitDate[2].length() != 2) {
            throw new RuntimeException("Illegal date format.");
        }
        LocalDate formattedDate = LocalDate.parse(clientDate.subSequence(0, 10));
        if(formattedDate.isAfter(LocalDate.now().plusDays(7)) || formattedDate.isBefore(LocalDate.now())) {
            throw new RuntimeException("Date is out of range.");
        }

        return formattedDate;
    }

    public static java.sql.Date convertToDatabase (String dateFromDTO) {
        long dateAsLong = Long.parseLong(dateFromDTO);
        Instant dateAsInstant = Instant.ofEpochSecond(dateAsLong);
        Date date = Date.from(dateAsInstant);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateAsString = formatter.format(date);
        return java.sql.Date.valueOf(formattedDateAsString);

    }

}
