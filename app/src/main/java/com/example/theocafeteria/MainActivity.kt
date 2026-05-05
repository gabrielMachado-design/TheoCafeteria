package com.example.theocafeteria

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.theocafeteria.databinding.ActivityMainBinding
import com.example.theocafeteria.ui.cart.CartFragment
import com.example.theocafeteria.ui.product.ProductListFragment
import com.example.theocafeteria.viewmodel.CartViewModel
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var badge: BadgeDrawable
    private val cartViewModel: CartViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClicks()
        setupBadge()
        observeCart()

        if (savedInstanceState == null) {
            openCategory("quentes")
        }
    }

    private fun setupClicks() {
        binding.btnSalgados.setOnClickListener { openCategory("salgados") }
        binding.btnDoces.setOnClickListener { openCategory("doces") }
        binding.btnQuentes.setOnClickListener { openCategory("quentes") }
        binding.btnGelados.setOnClickListener { openCategory("gelados") }
    }

    private fun openCategory(category: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ProductListFragment.newInstance(category))
            .commit()
    }

    @OptIn(ExperimentalBadgeUtils::class)
    private fun setupBadge() {

            badge = BadgeDrawable.create(this)
            badge.isVisible = false
            badge.number = 0
            badge.backgroundColor = getColor(R.color.coffe_secondary)
            badge.badgeTextColor = getColor(R.color.white)

            binding.btnCart.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, CartFragment())
                    .addToBackStack(null)
                    .commit()
            }


            badge.verticalOffset = 15
            badge.horizontalOffset = 15

            BadgeUtils.attachBadgeDrawable(
                badge,
                binding.btnCart,
                binding.cartContainer
            )
            Log.d("CART_TEST", "Badge anexado ao btnCart")
        }


    private fun observeCart() {
        lifecycleScope.launch {
            cartViewModel.state.collect { state ->

                val count = state.items.sumOf { it.quantity }

                badge.number = count
                badge.isVisible = count > 0
            }
        }
    }
}
