package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.tbandawa.android.openweather.SettingsViewModel
import openweather.data.local.PreferenceHelper
import openweather.data.local.PreferenceUnits

@ExperimentalMaterialApi
@Composable
fun TemperatureSettings(
    preferenceUnits: PreferenceUnits,
    setPreference: (PreferenceUnits) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Temperature",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
        Surface(
            color = Color.LightGray,
            contentColor = Color.LightGray,
            shape = CircleShape,
            modifier = Modifier.padding(1.dp)
        ) {
            Row {
                UnitChip(preferenceUnits, setPreference, text = "°C")
                UnitChip(preferenceUnits, setPreference, text = "F")
            }
        }
    }
}