package me.tbandawa.android.openweather.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
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
    navigateToSettings: () -> Unit
) {

    Surface(color = MaterialTheme.colors.background) {

        val preferenceUnits = preferenceHelper.observeAsState(preferenceHelper.get()).value

        when(val result = viewModel.oneCallWeather.observeAsState().value) {
            is NetworkResult.Loading -> {
                LoadingScreen()
            }
            is NetworkResult.Success -> {
                WeatherScreen(
                    preferenceUnits = preferenceUnits,
                    oneCall = result.data!!,
                    navigateToSettings
                )
            }
            is NetworkResult.Error -> {

            }
        }

    }

}

@ExperimentalAnimationApi
@Composable
fun WeatherScreen(
    preferenceUnits: PreferenceUnits,
    oneCall: OneCall,
    navigateToSettings: () -> Unit
) {
    Scaffold(
        topBar = { WeatherToolBar(navigateToSettings) }
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
                    Image(
                        painter = painterResource(R.drawable.weather),
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp, 200.dp)
                    )
                    Text(
                        text = oneCall.current?.temp!!.toTemperature(preferenceUnits.temperature),
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        )
                    )
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
            Column(
                modifier = Modifier
                    .constrainAs(bottomLayout) {
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth()
            ) {
                BottomRecycler(oneCall.hourly!!, preferenceUnits)
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