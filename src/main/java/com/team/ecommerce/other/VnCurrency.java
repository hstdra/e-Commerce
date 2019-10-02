package com.team.ecommerce.other;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.util.Locale;
@Component
public class VnCurrency {
    public static String format(String price){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(Long.parseLong(price));
    }
}
