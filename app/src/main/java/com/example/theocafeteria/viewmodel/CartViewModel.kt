package com.example.theocafeteria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theocafeteria.data.model.CartState
import com.example.theocafeteria.data.model.Product
import com.example.theocafeteria.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {

    val state = repository.items.map { items ->
        CartState(
            items = items,
            total = items.sumOf { it.product.price * it.quantity },
            isEmpty = items.isEmpty()
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        CartState()
    )

    fun addItem(product: Product) = repository.addItem(product)
    fun removeItem(product: Product) = repository.removeItem(product)
    fun clearCart() = repository.clearCart()
}