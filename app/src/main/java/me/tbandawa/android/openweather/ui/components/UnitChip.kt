package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import openweather.data.local.PreferenceUnits

@ExperimentalMaterialApi
@Composable
fun UnitChip(
    preferenceUnits: State<PreferenceUnits>,
    setPreference: (PreferenceUnits) -> Unit,
    text: String
) {

    val units by preferenceUnits

    Surface(
        color = when (text) {
            units.temperature,
            units.speed,
            units.pressure,
            units.time -> MaterialTheme.colors.onSurface
            else -> Color.Transparent
        },
        contentColor = when (text) {
            units.temperature,
            units.speed,
            units.pressure,
            units.time -> MaterialTheme.colors.onPrimary
            else -> Color.White
        },
        shape = CircleShape,
        modifier = Modifier.padding(3.dp),
        onClick = {

            when (text) {
                "°C","°F" -> {
                    units.temperature = text
                }
                "m/s","km/h","mph" -> {
                    units.speed = text
                }
                "hPa","inHg" -> {
                    units.pressure = text
                }
                "24-hour", "12-hour" -> {
                    units.time = text
                }
            }

            setPreference(units)

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