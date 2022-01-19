package me.tbandawa.android.openweather

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import androidx.constraintlayout.compose.ConstraintLayout
import dagger.hilt.android.AndroidEntryPoint
import me.tbandawa.android.openweather.ui.components.*
import openweather.data.local.PreferenceHelper
import openweather.domain.models.NetworkResult
import javax.inject.Inject

@ExperimentalPermissionsApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    private val viewModel: WeatherViewModel by viewModels()

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val preferenceUnits = preferenceHelper.observeAsState(preferenceHelper.get())

            viewModel.fetchOneCall((-20.1837).toLong(), 28.5203.toLong())

            OpenWeatherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    when(val result = viewModel.oneCallWeather.value) {
                        is NetworkResult.Loading -> {
                            LoadingContent()
                        }
                        is NetworkResult.Success -> {

                            Scaffold(
                                topBar = { MainToolBar() }
                            ) {
                                ConstraintLayout(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    val (topLayout, bottomLayout) = createRefs()
                                    Column(
                                        modifier = Modifier
                                            .constrainAs(topLayout) {
                                                top.linkTo(parent.top)
                                            }
                                            .fillMaxWidth()
                                            .fillMaxHeight()
                                    ) {
                                        WeatherContent(result.data?.current!!, preferenceUnits.value)
                                    }
                                    Column(
                                        modifier = Modifier
                                            .constrainAs(bottomLayout) {
                                                bottom.linkTo(parent.bottom)
                                            }
                                            .fillMaxWidth()
                                    ) {
                                        BottomRecycler(result.data?.hourly!!, preferenceUnits.value)
                                    }
                                }
                            }
                        }
                        is NetworkResult.Error -> {

                        }
                    }
                }
            }

        }
    }

}

@ExperimentalPermissionsApi
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    OpenWeatherTheme {
        PermissionContent(launchPermissionRequest = {})
    }
}