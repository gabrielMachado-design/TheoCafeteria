package com.example.theocafeteria.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theocafeteria.data.model.Product
import com.example.theocafeteria.databinding.FragmentProductListBinding
import com.example.theocafeteria.ui.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val products = listOf(
            Product(1, "Café Expresso", 5.0),
            Product(2, "Cappuccino", 8.0),
            Product(3, "Latte", 10.0),
            Product(4, "Mocha", 12.0)
        )

        val adapter = ProductAdapter(products) { product ->
            Toast.makeText(requireContext(), "${product.name} adicionado ☕", Toast.LENGTH_SHORT)
                .show()
        }

        binding.recyclerProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerProducts.adapter = adapter
    }
}