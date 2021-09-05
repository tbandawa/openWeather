package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDivider() {
    Spacer(modifier = Modifier
        .height(1.dp)
        .fillMaxWidth()
        .background(color = Color.LightGray))
}