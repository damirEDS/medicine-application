package com.medicineservices.springboot.translation;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class TranslationService {

    private final MessageSource messageSource;

    public TranslationService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getTranslation(String messageId, Locale locale) {
        return messageSource.getMessage(messageId, null, locale);
    }
}
