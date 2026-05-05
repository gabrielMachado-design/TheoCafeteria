package com.example.theocafeteria.ui.checkout

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theocafeteria.databinding.ActivityCheckoutBinding
import com.example.theocafeteria.ui.SuccessActivity
import com.example.theocafeteria.ui.common.CartItemAdapter
import com.example.theocafeteria.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding
    private val cartViewModel: CartViewModel by viewModels()

    var adapter = CartItemAdapter(
        onAdd = { cartViewModel.addItem(it) },
        onRemove = { cartViewModel.removeItem(it) },
        showControls = true
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()
        setupUI()
        observeState()
    }

    private fun setupRecycler() {
        adapter = CartItemAdapter(
            onAdd = { product -> cartViewModel.addItem(product) },
            onRemove = { product -> cartViewModel.removeItem(product) }
        )

        binding.recyclerCheckout.layoutManager = LinearLayoutManager(this)
        binding.recyclerCheckout.adapter = adapter
    }

    private fun observeState() {
        lifecycleScope.launch {
            cartViewModel.state.collect { state ->

                adapter.submitList(state.items)

                binding.txtTotal.text = "Total: R$ %.2f".format(state.total)

                binding.btnFinish.isEnabled = !state.isEmpty
                binding.btnFinish.alpha = if (!state.isEmpty) 1f else 0.5f
            }
        }
    }

    private fun setupUI() {
        binding.btnFinish.setOnClickListener {

            val state = cartViewModel.state.value

            if (state.isEmpty) {
                Toast.makeText(
                    this,
                    "Adicione itens antes de finalizar ☕",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            cartViewModel.clearCart()

            startActivity(Intent(this, SuccessActivity::class.java))
            finish()
        }
        
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}