package com.team.ecommerce.other;

import java.text.NumberFormat;
import java.util.Locale;

public class VnCurrency {
    public String format(Long price) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(price);
    }
}
