package com.example.theocafeteria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theocafeteria.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {

    private val _items = MutableStateFlow<List<Product>>(emptyList())
    val items = _items.asStateFlow()

    val cartCount = items.map { it.size }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        0
    )

    fun addItem(product: Product) {
        _items.value = _items.value + product
    }

    fun removeItem(product: Product) {
        _items.value = _items.value.toMutableList().apply {
            remove(product)
        }
    }

    fun getTotal(): Double {
        return _items.value.sumOf { it.price }
    }
}