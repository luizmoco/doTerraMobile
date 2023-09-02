package com.luiz.moco.doterra.util;

import android.content.Context;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class StringUtil {
    Context context;

    public StringUtil(Context context) {
        this.context = context;
    }

    public String getText(int resourceID, BigDecimal valor) {
        return getText(context.getResources().getString(resourceID), valor);
    }

    private String getText(String text, BigDecimal valor) {
        return String.format("%s %s", text, NumberFormat.getCurrencyInstance().format(valor));
    }

    public String getTextComHifen(String text, BigDecimal valor) {
        return String.format("%s - %s", text, NumberFormat.getCurrencyInstance().format(valor));
    }
}
