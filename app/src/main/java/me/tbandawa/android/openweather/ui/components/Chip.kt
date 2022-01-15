package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import me.tbandawa.android.openweather.SettingsViewModel
import openweather.data.local.PreferenceUnits
import openweather.data.local.Units

@ExperimentalMaterialApi
@Composable
fun Chip(
    preferenceUnits: PreferenceUnits,
    viewModel: SettingsViewModel,
    text: String
) {

    val unitObject = remember { mutableStateOf(preferenceUnits) }

    Surface(
        color = when (text) {
            unitObject.value.temperature -> MaterialTheme.colors.onSurface
            else -> Color.Transparent
        },
        contentColor = when (text) {
            unitObject.value.temperature -> MaterialTheme.colors.onPrimary
            else -> Color.White
        },
        shape = CircleShape,
        modifier = Modifier.padding(3.dp),
        onClick = {
            var units = viewModel.getUnits()
            units.temperature = text
            viewModel.setUnits(units)
            unitObject.value = units
        }
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

    }
}