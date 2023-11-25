package com.medicineservices.springboot.translation.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Service
public class TranslationService implements WebMvcConfigurer {
    private final LocaleChangeInterceptor localeChangeInterceptor;

    public TranslationService(LocaleChangeInterceptor localeChangeInterceptor){
        this.localeChangeInterceptor = localeChangeInterceptor;
    }
    public void addInterseptors(InterceptorRegistry interceptorRegistry){
        interceptorRegistry.addInterceptor(localeChangeInterceptor);
    }

}
