package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VerticalDivider() {
    Spacer(modifier = Modifier
        .width(1.dp)
        .height(57.dp)
        .background(color = Color.LightGray))
}