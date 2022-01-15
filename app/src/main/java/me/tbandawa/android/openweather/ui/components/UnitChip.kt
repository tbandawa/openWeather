package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import openweather.data.local.PreferenceUnits

@ExperimentalMaterialApi
@Composable
fun UnitChip(
    preferenceUnits: PreferenceUnits,
    setPreference: (PreferenceUnits) -> Unit,
    text: String
) {

    val unit = remember { mutableStateOf(preferenceUnits) }

    Surface(
        color = when (text) {
            unit.value.temperature -> MaterialTheme.colors.onSurface
            else -> Color.Transparent
        },
        contentColor = when (text) {
            unit.value.temperature -> MaterialTheme.colors.onPrimary
            else -> Color.White
        },
        shape = CircleShape,
        modifier = Modifier.padding(3.dp),
        onClick = {
            unit.value.temperature = text
            setPreference(unit.value)
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