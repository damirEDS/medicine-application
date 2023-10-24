package com.medicineservices.springboot.exceptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class IINValidator {
    public static void validateIIN(String iin) throws Exception {
        // Проверяем, что ИИН состоит из 12 цифр
        if (!iin.matches("\\d{12}")) {
            throw new Exception("ИИН должен состоять из 12 цифр");
        }

        // Выделяем первые 6 символов (дату рождения)
        String birthDatePart = iin.substring(0, 6);

        // Пытаемся преобразовать и проверить дату рождения
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
            sdf.setLenient(false);
            sdf.parse(birthDatePart);
        } catch (ParseException e) {
            throw new Exception("Некорректный ИИН");
        }
    }
}
