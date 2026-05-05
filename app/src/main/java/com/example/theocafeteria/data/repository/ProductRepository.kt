package com.example.theocafeteria.data.repository

import com.example.theocafeteria.R
import com.example.theocafeteria.data.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor() {

    private val products = listOf(
        Product(1, "Café Expresso", 5.0, "quentes", R.drawable.cafe_expresso),
        Product(2, "Cappuccino", 8.0, "quentes", R.drawable.cappuccino),
        Product(3, "Latte", 10.0, "quentes", R.drawable.latte),
        Product(4, "Bolo de Chocolate", 12.0, "doces",R.drawable.bolo_chocolate),
        Product(5, "Pão de Queijo", 6.0, "salgados",R.drawable.pao_de_queijo),
        Product(6, "Café Gelado", 9.0, "gelados",R.drawable.cafe_gelado),
        Product(7, "Café com leite", 6.0, "quentes",R.drawable.cafe_com_leite),
        Product(8, "Coca cola 350ml(lata)", 9.0, "gelados",R.drawable.coca_cola_lata),

    )

    fun getProductsByCategory(category: String): List<Product> {
        return products.filter { it.category.lowercase() == category.lowercase() }
    }
}
