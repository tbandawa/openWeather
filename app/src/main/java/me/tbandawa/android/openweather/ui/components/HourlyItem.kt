package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import me.tbandawa.android.openweather.BuildConfig.OPEN_WEATHER_ICON_2X
import me.tbandawa.android.openweather.BuildConfig.OPEN_WEATHER_ICON_URL
import me.tbandawa.android.openweather.extensions.toTemperature
import me.tbandawa.android.openweather.extensions.toTime
import me.tbandawa.android.openweather.ui.theme.dimensions
import openweather.domain.models.Hourly

@Composable
fun HourlyItem(
    hourly: Hourly,
    timeUnit: String,
    temperatureUnit: String
) {

    val hourlyIcon = rememberImagePainter(
        data = "${OPEN_WEATHER_ICON_URL}${hourly.weather?.get(0)?.icon}${OPEN_WEATHER_ICON_2X}",
        builder = {
            crossfade(true)
        }
    )

    Column(modifier = Modifier
        .width(dimensions.hourlyItemWidth)
        .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = hourly.dt!!.toTime(timeUnit),
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
        )
        Image(
            painter = hourlyIcon,
            contentDescription = null,
            modifier = Modifier
                .size(dimensions.hourlyIconSize, dimensions.hourlyIconSize)
                .padding(0.dp, 0.dp, 0.dp, 0.dp)
        )
        Text(
            text = hourly.temp!!.toTemperature(temperatureUnit),
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp
            )
        )
    }
}