package me.tbandawa.android.openweather.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.tbandawa.android.openweather.R
import me.tbandawa.android.openweather.extensions.toDate

@ExperimentalAnimationApi
@Composable
fun WeatherToolBar(
    country: String,
    city: String,
    day: Int,
    navigateToSettings: () -> Unit
) {

    TopAppBar(
        title = {
            Column {
                Text(text = "$city, $country")
                Text(
                    text = day.toDate(),
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        },
        backgroundColor = Color.White,
        elevation = 0.dp,
        actions = {
            IconButton(
                onClick = {
                    navigateToSettings.invoke()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp),
                    tint = Color.Black
                )
            }
        }
    )
}