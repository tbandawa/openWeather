package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import openweather.domain.models.PreferenceUnits

@Composable
fun UnitChip(
    preferenceUnits: PreferenceUnits,
    setPreference: (PreferenceUnits) -> Unit,
    text: String
) {

    Surface(
        color = when (text) {
            preferenceUnits.temperature,
            preferenceUnits.speed,
            preferenceUnits.pressure,
            preferenceUnits.time -> MaterialTheme.colorScheme.onSurface
            else -> Color.Transparent
        },
        contentColor = when (text) {
            preferenceUnits.temperature,
            preferenceUnits.speed,
            preferenceUnits.pressure,
            preferenceUnits.time -> MaterialTheme.colorScheme.onPrimary
            else -> Color.White
        },
        shape = CircleShape,
        modifier = Modifier.padding(horizontal = 4.dp),
        onClick = {

            when (text) {
                "°C","°F" -> {
                    preferenceUnits.temperature = text
                }
                "m/s","km/h","mph" -> {
                    preferenceUnits.speed = text
                }
                "hPa","inHg" -> {
                    preferenceUnits.pressure = text
                }
                "24-hour", "12-hour" -> {
                    preferenceUnits.time = text
                }
            }

            val newUnits = PreferenceUnits(
                preferenceUnits.temperature,
                preferenceUnits.speed,
                preferenceUnits.pressure,
                preferenceUnits.time
            )

            setPreference(newUnits)

        }
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitChipBarPreview() {
    UnitChip(
        preferenceUnits = PreferenceUnits("°C", "m/s", "hPa", "24-hour"),
        setPreference = { _ ->  },
        text = "°C"
    )
}