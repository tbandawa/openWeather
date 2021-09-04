package me.tbandawa.android.openweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import me.tbandawa.android.openweather.ui.components.SettingsContent
import me.tbandawa.android.openweather.ui.components.SettingsToolBar
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme

@ExperimentalAnimationApi
class SettingsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenWeatherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = { SettingsToolBar() }
                    ) {
                        SettingsContent()
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    OpenWeatherTheme {
        Scaffold(
            topBar = { SettingsToolBar() }
        ) {
            SettingsContent()
        }
    }
}