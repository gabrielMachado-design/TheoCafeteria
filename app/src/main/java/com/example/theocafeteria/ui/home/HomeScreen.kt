package com.example.theocafeteria.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.theocafeteria.R

@Composable
fun HomeScreen(onStartClick: () -> Unit){

    val background = colorResource(R.color.background)
    val primary = colorResource(R.color.coffe_primary)
    val title = stringResource(R.string.home_title)
    val subtitle = stringResource(R.string.home_subtitle)
    val buttonText = stringResource(R.string.start_order)
    val padding = dimensionResource(R.dimen.padding_large)
    val buttonHeight = dimensionResource(R.dimen.button_heigth)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(padding)
    ){
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement =
                Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = subtitle,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = onStartClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(buttonHeight),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primary)
            ) {
                Text(
                    text = buttonText,
                    fontSize = 22.sp,
                    color = Color.White
                )
            }

        }

    }

}