package com.example.theocafeteria.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.theocafeteria.data.model.CartItem
import com.example.theocafeteria.data.model.Product
import com.example.theocafeteria.databinding.ItemCartBinding
import com.example.theocafeteria.extensions.toCurrency

class CartItemAdapter(
    private val onAdd: (Product) -> Unit,
    private val onRemove: (Product) -> Unit,
    private val showControls: Boolean = true
) : ListAdapter<CartItem, CartItemAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(val binding: ItemCartBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCartBinding.inflate(
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


       holder.binding.imgProduct.setImageResource(item.product.imageRes)


        holder.binding.btnAdd.visibility =
            if (showControls) View.VISIBLE else View.GONE

        holder.binding.btnRemove.visibility =
            if (showControls) View.VISIBLE else View.GONE

        holder.binding.btnAdd.setOnClickListener {
            it.animate().scaleX(1.2f).scaleY(1.2f).setDuration(100).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).duration = 100
            }
            onAdd(item.product)
        }

        holder.binding.btnRemove.setOnClickListener {
            onRemove(item.product)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem) =
            oldItem.product.id == newItem.product.id

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem) =
            oldItem == newItem
    }
}