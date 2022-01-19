package me.tbandawa.android.openweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import me.tbandawa.android.openweather.ui.ForecastContent
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme

@ExperimentalAnimationApi
class ForecastActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenWeatherTheme {
                ForecastContent()
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun ForecastPreview() {
    OpenWeatherTheme {
        ForecastContent()
    }
}