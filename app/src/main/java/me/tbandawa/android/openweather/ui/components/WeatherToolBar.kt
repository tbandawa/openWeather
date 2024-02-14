package me.tbandawa.android.openweather.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.tbandawa.android.openweather.R
import me.tbandawa.android.openweather.extensions.toDate
import me.tbandawa.android.openweather.ui.theme.dimensions

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@Composable
fun WeatherToolBar(
    location: String,
    day: Int,
    navigateToSettings: () -> Unit
) {

    TopAppBar(
        title = {
            Column {
                Text(
                    text = location,
                    style = TextStyle(
                        fontSize = dimensions.weatherToolBarTitleTextSize,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = day.toDate(),
                    style = TextStyle(
                        fontSize = dimensions.weatherToolBarDateTextSize
                    )
                )
            }
        },
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
                        .size(25.dp),
                    tint = Color.Black
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun WeatherToolBarPreview() {
    WeatherToolBar(
        location = "Home Toolbar",
        day = 1,
        navigateToSettings = { }
    )
}