package com.example.theocafeteria.ui

import android.R.id.primary
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.theocafeteria.R

@Composable
fun TheoCafeteriaTheme(
    content: @Composable () -> Unit
) {
    val colors = lightColorScheme(
        primary = colorResource(R.color.coffe_primary),
        background = colorResource(R.color.background),
        onPrimary = Color.White,
        onBackground = colorResource(R.color.text_primary)
    )

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}