package com.example.theocafeteria.data.repository

import com.example.theocafeteria.data.model.CartItem
import com.example.theocafeteria.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor() {

    private val _items = MutableStateFlow<List<CartItem>>(emptyList())
    val items = _items.asStateFlow()

    fun addItem(product: Product) {
        val current = _items.value.toMutableList()
        val index = current.indexOfFirst { it.product.id == product.id }

        if (index != -1) {
            val item = current[index]
            current[index] = item.copy(quantity = item.quantity + 1)
        } else {
            current.add(CartItem(product, 1))
        }

        _items.value = current
    }

    fun removeItem(product: Product) {
        val current = _items.value.toMutableList()
        val index = current.indexOfFirst { it.product.id == product.id }

        if (index != -1) {
            val item = current[index]
            if (item.quantity > 1) {
                current[index] = item.copy(quantity = item.quantity - 1)
            } else {
                current.removeAt(index)
            }
        }

        _items.value = current
    }

    fun clearCart() {
        _items.value = emptyList()
    }

    fun getTotal(): Double {
        return _items.value.sumOf { it.product.price * it.quantity }
    }
}