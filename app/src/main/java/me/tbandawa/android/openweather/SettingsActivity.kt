package me.tbandawa.android.openweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import me.tbandawa.android.openweather.ui.components.SettingsContent
import me.tbandawa.android.openweather.ui.components.SettingsToolBar
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme
import openweather.data.local.PreferenceHelper
import openweather.data.local.PreferenceUnits
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SettingsActivity : ComponentActivity() {

    @Inject lateinit var preferenceHelper: PreferenceHelper

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Timber.d("${preferenceHelper.observeAsState(PreferenceUnits("°F", "m/s", "hPa", "km", "24-hour")).value}")
            val preferenceUnits = preferenceHelper.observeAsState(PreferenceUnits("°F", "m/s", "hPa", "km", "24-hour"))

            OpenWeatherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = { SettingsToolBar() }
                    ) {
                        SettingsContent(preferenceUnits.value, preferenceHelper::put)
                    }
                }
            }

        }
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    OpenWeatherTheme {

    }
}