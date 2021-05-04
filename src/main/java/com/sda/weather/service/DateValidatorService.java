package com.sda.weather.service;

import java.time.LocalDate;

public class DateValidatorService {

    public static LocalDate validate(String clientDate) {
        if (clientDate.isEmpty()) {
            return LocalDate.now();
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
        if(formattedDate.isAfter(LocalDate.now().plusDays(14)) || formattedDate.isBefore(LocalDate.now())) {
            throw new RuntimeException("Date is out of range.");
        }

        return formattedDate;
    }
}
