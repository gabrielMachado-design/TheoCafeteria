package com.example.theocafeteria.extensions

import java.text.NumberFormat
import java.util.Locale

fun Double.toCurrency(): String {
    return NumberFormat
        .getCurrencyInstance(Locale("pt", "BR"))
        .format(this)
}