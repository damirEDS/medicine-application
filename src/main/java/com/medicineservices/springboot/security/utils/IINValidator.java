package com.medicineservices.springboot.security.utils;

public class IINValidator {
    public static boolean validateIIN(String iin) {
        int lastDigit = Character.getNumericValue(iin.charAt(11));
        int sum = 0;

        for (int i = 0; i < 11; i++) {
            int digit = Character.getNumericValue(iin.charAt(i));
            sum += digit * (i + 1);
        }

        int calculatedCheckDigit = sum % 11;

        return (calculatedCheckDigit == lastDigit || (calculatedCheckDigit == 10 && lastDigit == 0));
    }
}
