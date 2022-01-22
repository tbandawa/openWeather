package me.tbandawa.android.openweather.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import me.tbandawa.android.openweather.R
import me.tbandawa.android.openweather.extensions.*
import openweather.data.local.PreferenceUnits
import openweather.domain.models.Daily

@ExperimentalAnimationApi
@Composable
fun ForecastItem(
    daily: Daily,
    preferenceUnits: PreferenceUnits
) {

    var visible by remember { mutableStateOf(false) }

    val weatherIcon = rememberImagePainter(
        data = "https://openweathermap.org/img/wn/${daily.weather?.get(0)?.icon}@4x.png",
        builder = {
            crossfade(true)
        }
    )

    ConstraintLayout(
        modifier = Modifier
            .background(
                color = when (visible) {
                    true -> { Color.LightGray }
                    false -> { Color.White }
                }
            )
            .fillMaxWidth()
            .padding(10.dp, 5.dp, 10.dp, 5.dp)
            .clickable {
                visible = !visible
            }
    ) {
        val (visibleLayout, moreLayout) = createRefs()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(visibleLayout) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(10.dp, 0.dp, 10.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = daily.dt!!.toDay(),
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Text(
                text = daily.temp?.max!!.toTemperature(preferenceUnits.temperature),
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .padding(16.dp, 0.dp, 0.dp, 0.dp)
            )
            Text(
                text = daily.temp?.min!!.toTemperature(preferenceUnits.temperature),
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .padding(8.dp, 0.dp, 0.dp, 0.dp)
            )
            Image(
                painter = weatherIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp, 40.dp)
                    .padding(16.dp, 0.dp, 8.dp, 0.dp)
            )
            Text(
                text = "${daily.weather?.get(0)?.description?.replaceFirstChar { it.uppercase() }}",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                ),
                modifier = Modifier
                    .padding(16.dp, 0.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
            )
        }

        AnimatedVisibility(
            modifier = Modifier
                .constrainAs(moreLayout) {
                    start.linkTo(parent.start)
                    top.linkTo(visibleLayout.bottom)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(0.dp, 0.dp, 0.dp, 16.dp),
            visible = visible,
            enter = fadeIn(
                initialAlpha = 0.4f
            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 250)
            )
        ) {
            Column {
                Row {
                    MoreItem(painterResource(R.drawable.ic_cloud), "Cloud Cover", daily.clouds!!.toCloudCover())
                    MoreItem(painterResource(R.drawable.ic_pressure), "Pressure", daily.pressure!!.toPressure(preferenceUnits.pressure))
                    MoreItem(painterResource(R.drawable.ic_wind), "Wind Speed", daily.windSpeed!!.toSpeed(preferenceUnits.speed))
                }
                Row {
                    MoreItem(painterResource(R.drawable.ic_uv), "UV Index", daily.uvi!!.toUV())
                    MoreItem(painterResource(R.drawable.ic_humidity), "Humidity", daily.humidity!!.toHumidity())
                    MoreItem(painterResource(R.drawable.ic_dew), "Dew Point", daily.dewPoint!!.toDewPoint(preferenceUnits.temperature))
                }
            }
        }

    }
}