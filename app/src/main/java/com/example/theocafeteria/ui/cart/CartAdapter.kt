package com.example.theocafeteria.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.theocafeteria.data.model.Product
import com.example.theocafeteria.databinding.ItemCartBinding

class CartAdapter(
    private val onRemove: (Product) -> Unit
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var items: List<Product> = emptyList()

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
        val product = items[position]

        holder.binding.txtName.text = product.name
        holder.binding.txtPrice.text = "R$ ${product.price}"

        holder.binding.btnRemove.setOnClickListener {
            onRemove(product)
        }
    }

    override fun getItemCount() = items.size

    fun submitList(newList: List<Product>) {
        items = newList
        notifyDataSetChanged()
    }
}