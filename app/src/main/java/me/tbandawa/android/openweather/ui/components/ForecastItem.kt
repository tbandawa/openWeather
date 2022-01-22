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
import me.tbandawa.android.openweather.R

@ExperimentalAnimationApi
@Composable
fun ForecastItem() {

    var visible by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier
            .background(color = Color.White)
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
                text = "Sun",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Text(
                text = "22°C",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .padding(16.dp, 0.dp, 0.dp, 0.dp)
            )
            Text(
                text = "22°C",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .padding(8.dp, 0.dp, 0.dp, 0.dp)
            )
            Image(
                painter = painterResource(R.drawable.weather),
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp, 40.dp)
                    .padding(16.dp, 0.dp, 8.dp, 0.dp)
            )
            Text(
                text = "Partly Cloudy",
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
                    MoreItem(painterResource(R.drawable.ic_cloud), "Cloud Cover", "")
                    MoreItem(painterResource(R.drawable.ic_rain), "Chance", "")
                    MoreItem(painterResource(R.drawable.ic_wind), "Wind Speed", "")
                }
                Row {
                    MoreItem(painterResource(R.drawable.ic_uv), "UV Index", "")
                    MoreItem(painterResource(R.drawable.ic_humidity), "Humidity", "")
                    MoreItem(painterResource(R.drawable.ic_dew), "Dew Point", "")
                }
            }
        }

    }
}