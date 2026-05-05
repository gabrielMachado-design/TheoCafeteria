package com.example.theocafeteria.data.repository

import com.example.theocafeteria.data.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor() {

    private val products = listOf(
        Product(1, "Café Expresso", 5.0, "quentes"),
        Product(2, "Cappuccino", 8.0, "quentes"),
        Product(3, "Latte", 10.0, "quentes"),
        Product(4, "Bolo de Chocolate", 12.0, "doces"),
        Product(5, "Pão de Queijo", 6.0, "salgados"),
        Product(6, "Combo Café + Pão", 15.0, "combos"),
        Product(7, "Café Gelado", 9.0, "gelados"),
        Product(8, "Café com leite", 6.0, "quentes"),
        Product(9, "Coca cola 350ml(lata)", 9.0, "gelados"),

    )

    fun getProductsByCategory(category: String): List<Product> {
        return products.filter { it.category.lowercase() == category.lowercase() }
    }
}
