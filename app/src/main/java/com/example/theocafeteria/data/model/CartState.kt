package com.example.theocafeteria.data.model

data class CartState(
    val items: List<CartItem> = emptyList(),
    val total: Double = 0.0,
    val isEmpty: Boolean = true
)