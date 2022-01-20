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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.tbandawa.android.openweather.R

@ExperimentalAnimationApi
@Composable
fun WeatherToolBar(navigateToSettings: () -> Unit) {

    val context = LocalContext.current

    TopAppBar(
        title = {
            Column() {
                Text(text = "Johannesburg")
                Text(
                    text = "22, Aug 2021",
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