package com.example.theocafeteria.ui.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.gestures.snapping.SnapPosition.Center.position
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.theocafeteria.data.model.CartItem
import com.example.theocafeteria.data.model.Product
import com.example.theocafeteria.databinding.ItemCartBinding
import com.example.theocafeteria.databinding.ItemCheckoutBinding
import com.example.theocafeteria.extensions.toCurrency
import java.text.NumberFormat
import java.util.Locale


class CheckoutAdapter(
    private val onAdd: (Product) -> Unit,
    private val onRemove: (Product) -> Unit
) : ListAdapter<CartItem, CheckoutAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(val binding: ItemCheckoutBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCheckoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)


        holder.binding.txtName.text = item.product.name
        holder.binding.txtPrice.text = item.product.price.toCurrency()
        holder.binding.txtQuantity.text = "x${item.quantity}"

        holder.binding.btnAdd.setOnClickListener {
            onAdd(item.product)
        }

        holder.binding.btnRemove.setOnClickListener {
            onRemove(item.product)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.product.id == newItem.product.id
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }
    }
}