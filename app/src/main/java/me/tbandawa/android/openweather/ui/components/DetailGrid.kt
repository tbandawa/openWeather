package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.tbandawa.android.openweather.R

@Composable
fun DetailGrid() {
    Column(
        modifier = Modifier
            .padding(0.dp, 35.dp, 0.dp, 0.dp)
            .width(IntrinsicSize.Max)
    ) {
        Row {
            DetailItem(painterResource(R.drawable.ic_feels), "Feels like", 12.0)
            VerticalDivider()
            DetailItem(painterResource(R.drawable.ic_wind), "Wind Speed", 12.0)
            VerticalDivider()
            DetailItem(painterResource(R.drawable.ic_direction), "Wind direction", 12.0)
        }
        HorizontalDivider()
        Row {
            DetailItem(painterResource(R.drawable.ic_uv), "UV index", 12.0)
            VerticalDivider()
            DetailItem(painterResource(R.drawable.ic_cloud), "Cloud cover", 12.0)
            VerticalDivider()
            DetailItem(painterResource(R.drawable.ic_pressure), "Pressure", 12.0)
        }
        HorizontalDivider()
        Row {
            DetailItem(painterResource(R.drawable.ic_humidity), "Humidity", 12.0)
            VerticalDivider()
            DetailItem(painterResource(R.drawable.ic_dew), "Dew point", 12.0)
            VerticalDivider()
            DetailItem(painterResource(R.drawable.ic_visibility), "Visibility", 12.0)
        }
    }
}