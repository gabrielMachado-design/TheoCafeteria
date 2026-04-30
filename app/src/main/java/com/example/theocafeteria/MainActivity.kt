package com.example.theocafeteria

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import com.example.theocafeteria.ui.fragments.ProductListFragment
import com.example.theocafeteria.ui.screens.HomeScreen
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ComposeView(this).apply {
                setContent {
                    HomeScreen {
                        openProductFragment()
                    }
                }
            }
        )
    }

    private fun openProductFragment() {

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ProductListFragment())
            .addToBackStack(null)
            .commit()
    }
}