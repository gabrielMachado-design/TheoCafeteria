package com.example.theocafeteria.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theocafeteria.databinding.FragmentCartBinding
import com.example.theocafeteria.ui.checkout.CheckoutActivity
import com.example.theocafeteria.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val cartViewModel: CartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CartAdapter(
            onAdd = { product ->
                cartViewModel.addItem(product)
            },
            onRemove = { product ->
                cartViewModel.removeItem(product)
            }
        )

        binding.recyclerCart.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerCart.adapter = adapter

        lifecycleScope.launch {
            cartViewModel.state.collect { state ->

                adapter.submitList(state.items)

                binding.txtTotal.text = "Total: R$ %.2f".format(state.total)

                binding.btnFinish.isEnabled = !state.isEmpty
            }
        }
        binding.btnFinish.setOnClickListener {

            val state = cartViewModel.state.value

            if (state.isEmpty) {
                Toast.makeText(requireContext(), "Carrinho vazio", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            startActivity(Intent(requireContext(), CheckoutActivity::class.java))
        }
    }
}