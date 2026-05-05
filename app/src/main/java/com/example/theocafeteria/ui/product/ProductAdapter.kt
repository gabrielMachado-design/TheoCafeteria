package com.example.theocafeteria.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.theocafeteria.data.model.Product
import com.example.theocafeteria.databinding.ItemProductBinding
import com.example.theocafeteria.extensions.toCurrency

class ProductAdapter(
    private val products: List<Product>,
    private val onClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemProductBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]

        holder.binding.imgProduct.setImageResource(product.imageRes)
        holder.binding.txtName.text = product.name
        holder.binding.txtPrice.text = product.price.toCurrency()

        holder.binding.btnAdd.setOnClickListener {
            onClick(product)
        }
    }

    override fun getItemCount() = products.size
}