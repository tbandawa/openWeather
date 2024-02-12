package me.tbandawa.android.openweather.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import openweather.data.viewmodels.MainViewModel
import me.tbandawa.android.openweather.ui.components.ForecastItem
import me.tbandawa.android.openweather.ui.components.ForecastToolBar
import me.tbandawa.android.openweather.ui.components.HorizontalDivider
import openweather.domain.models.NetworkResult
import openweather.domain.models.PreferenceUnits
import org.koin.androidx.compose.koinViewModel

@ExperimentalAnimationApi
@Composable
fun ForecastContent(
    preferenceUnits: PreferenceUnits,
    viewModel: MainViewModel,
    location: String,
    navigateUp: () -> Unit
){

    val dailyItems = (viewModel.oneCallWeather.collectAsState().value as NetworkResult.Success).data.daily

    var expandedItem by remember{ mutableIntStateOf(0) }

    val showMore: (Int) -> Unit = {
        expandedItem = it
    }

    Surface(color = MaterialTheme.colorScheme.background) {
        Scaffold(
            topBar = { ForecastToolBar(
                location,
                navigateUp
            )},
            containerColor = Color.White
        ) {

            // Header layout
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp, 0.dp, 16.dp, 0.dp)
            ) {

                // Next 7 days text
                Text(
                    text = "Next 7 Days",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier
                        .padding(0.dp, 16.dp, 0.dp, 8.dp)
                )

                // Horizontal divider
                HorizontalDivider()

                // Forecast list
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                ) {
                    items(dailyItems!!) { daily ->
                        ForecastItem(
                            daily,
                            expandedItem,
                            preferenceUnits,
                            showMore
                        )
                    }
                }
            }
        }
    }
}