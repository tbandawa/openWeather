package me.tbandawa.android.openweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import dagger.hilt.android.AndroidEntryPoint
import me.tbandawa.android.openweather.ui.components.MainContent
import me.tbandawa.android.openweather.ui.components.MainToolBar
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme
import openweather.domain.models.NetworkResult
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val result = viewModel.oneCallWeather.value

            OpenWeatherTheme {
                Surface(color = MaterialTheme.colors.background) {

                    when(result) {
                        is NetworkResult.Loading -> {
                            LoadingContent()
                        }
                        is NetworkResult.Success -> {
                            Scaffold(
                                topBar = { MainToolBar() }
                            ) {
                                MainContent(result.data!!)
                            }
                        }
                        is NetworkResult.Error -> {
                            Timber.d(result.message)
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun LoadingContent(){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (centerLayout, textTitle) = createRefs()
        Text(
            text = "openWeather",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Light,
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

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    OpenWeatherTheme {
        LoadingContent()
    }
}