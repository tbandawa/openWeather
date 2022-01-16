package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.tbandawa.android.openweather.R
import me.tbandawa.android.openweather.extensions.*
import openweather.data.local.PreferenceUnits
import openweather.domain.models.Current
import timber.log.Timber

@Composable
fun DetailGrid(
    current: Current,
    preferenceUnits: PreferenceUnits
) {
    Column(
        modifier = Modifier
            .padding(0.dp, 35.dp, 0.dp, 0.dp)
            .width(IntrinsicSize.Max)
    ) {
        Row {
            DetailItem(painterResource(R.drawable.ic_feels), "Feels like", current.feelsLike!!.toTemperature(preferenceUnits.temperature))
            VerticalDivider()
            DetailItem(painterResource(R.drawable.ic_wind), "Wind Speed", current.windSpeed!!.toSpeed(preferenceUnits.speed))
            VerticalDivider()
            DetailItem(painterResource(R.drawable.ic_direction), "Wind direction", current.windDeg!!.toDirection())
        }
        HorizontalDivider()
        Row {
            DetailItem(painterResource(R.drawable.ic_uv), "UV index", current.uvi!!.toUV())
            VerticalDivider()
            DetailItem(painterResource(R.drawable.ic_cloud), "Cloud cover", current.clouds!!.toCloudCover())
            VerticalDivider()
            DetailItem(painterResource(R.drawable.ic_pressure), "Pressure", current.pressure!!.toPressure(preferenceUnits.pressure))
        }
        HorizontalDivider()
        Row {
            DetailItem(painterResource(R.drawable.ic_humidity), "Humidity", current.humidity!!.toHumidity())
            VerticalDivider()
            DetailItem(painterResource(R.drawable.ic_dew), "Dew point", current.dewPoint!!.toDewPoint(preferenceUnits.temperature))
            VerticalDivider()
            DetailItem(painterResource(R.drawable.ic_visibility), "Visibility", current.visibility!!.toVisibility())
        }
    }
}