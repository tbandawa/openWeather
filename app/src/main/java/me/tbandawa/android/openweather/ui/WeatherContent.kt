package me.tbandawa.android.openweather.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import me.tbandawa.android.openweather.BuildConfig.OPEN_WEATHER_ICON_4X
import me.tbandawa.android.openweather.BuildConfig.OPEN_WEATHER_ICON_URL
import me.tbandawa.android.openweather.MainViewModel
import me.tbandawa.android.openweather.R
import me.tbandawa.android.openweather.extensions.toTemperature
import me.tbandawa.android.openweather.ui.components.BottomRecycler
import me.tbandawa.android.openweather.ui.components.DetailGrid
import me.tbandawa.android.openweather.ui.components.WeatherToolBar
import openweather.data.local.PreferenceHelper
import openweather.data.local.PreferenceUnits
import openweather.domain.models.NetworkResult
import openweather.domain.models.OneCall
import java.util.*

@ExperimentalAnimationApi
@Composable
fun WeatherContent(
    preferenceHelper: PreferenceHelper,
    viewModel: MainViewModel,
    latitude: Double,
    longitude: Double,
    country: String,
    city: String,
    navigateToSettings: () -> Unit,
    navigateToForecast: () -> Unit
) {

    Surface(color = MaterialTheme.colors.background) {

        // Boolean state to hold if request was successful
        var isLoaded by rememberSaveable { mutableStateOf(false) }

        // Retry callback
        val retry: () -> Unit = { viewModel.fetchOneCall(latitude, longitude) }

        LaunchedEffect(Unit) {
            // If request unsuccessful, call again
            if (isLoaded.not())
                viewModel.fetchOneCall(latitude, longitude)
        }

        val preferenceUnits = preferenceHelper.observeAsState(preferenceHelper.get()).value

        // Update UI according to network result state
        when(val result = viewModel.oneCallWeather.value) {
            is NetworkResult.Loading -> {
                LoadingScreen()
            }
            is NetworkResult.Success -> {
                isLoaded = true
                WeatherScreen(
                    preferenceUnits = preferenceUnits,
                    oneCall = result.data!!,
                    country,
                    city,
                    navigateToSettings,
                    navigateToForecast
                )
            }
            is NetworkResult.Error -> {
                ErrorScreen(retry)
            }
        }

    }

}

@ExperimentalAnimationApi
@Composable
fun WeatherScreen(
    preferenceUnits: PreferenceUnits,
    oneCall: OneCall,
    country: String,
    city: String,
    navigateToSettings: () -> Unit,
    navigateToForecast: () -> Unit
) {

    val weatherIcon = rememberImagePainter(
        data = "${OPEN_WEATHER_ICON_URL}${oneCall.current?.weather?.get(0)?.icon}${OPEN_WEATHER_ICON_4X}",
        builder = {
            crossfade(true)
        }
    )

    Scaffold(
        topBar = { WeatherToolBar(
            country,
            city,
            oneCall.current?.dt!!,
            navigateToSettings
        ) }
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

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 35.dp, 0.dp, 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Main weather image
                    Image(
                        painter = weatherIcon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp, 200.dp)
                    )

                    // Temperature text
                    Text(
                        text = oneCall.current?.temp!!.toTemperature(preferenceUnits.temperature),
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        )
                    )

                    // Weather description text
                    Text(
                        text = oneCall.current!!.weather?.get(0)!!.description!!.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        },
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )
                    )

                    DetailGrid(oneCall.current!!, preferenceUnits)

                }

            }

            // Bottom composable containing hourly items
            Column(
                modifier = Modifier
                    .constrainAs(bottomLayout) {
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth()
            ) {
                BottomRecycler(
                    oneCall.hourly!!,
                    country,
                    city,
                    preferenceUnits,
                    navigateToForecast
                )
            }

        }

    }

}

@ExperimentalAnimationApi
@Composable
fun LoadingScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val (centerLayout, textTitle) = createRefs()

        // Open weather image
        Image(
            painter = painterResource(R.drawable.weather),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(centerLayout) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .size(225.dp, 225.dp)
                .padding(0.dp, 0.dp, 8.dp, 0.dp)
        )

        // Open weather text
        Text(
            text = "open Weather",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .constrainAs(textTitle) {
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(0.dp, 0.dp, 0.dp, 10.dp)
        )

    }

}

@Composable
fun ErrorScreen(
    retry: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, bottom = 50.dp, end = 20.dp)
    ) {

        val (titleLayout, descriptionLayout, retryButton) = createRefs()

        // Ooops text
        Text(
            text = "Ooops!",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .constrainAs(titleLayout) {
                    bottom.linkTo(descriptionLayout.top)
                    start.linkTo(parent.start)
                }
                .height(IntrinsicSize.Min)
                .padding(0.dp, 0.dp, 0.dp, 10.dp)
        )

        // Error message text
        Text(
            text = "Error! Unable to resolve host",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .constrainAs(descriptionLayout) {
                    start.linkTo(parent.start)
                    bottom.linkTo(retryButton.top)
                }
                .width(300.dp)
                .height(IntrinsicSize.Max)
                .padding(bottom = 10.dp)
        )

        // Retry button
        Button(
            onClick = {
                retry.invoke()
            },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .constrainAs(retryButton) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
                .padding(end = 5.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text(text = "Retry")
        }

    }
}