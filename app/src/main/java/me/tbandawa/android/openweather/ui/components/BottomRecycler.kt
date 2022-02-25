package me.tbandawa.android.openweather.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import me.tbandawa.android.openweather.ui.theme.dimensions
import openweather.domain.models.Hourly
import openweather.domain.models.PreferenceUnits

@ExperimentalAnimationApi
@Composable
fun BottomRecycler(
    hourly: List<Hourly>,
    preferenceUnits: PreferenceUnits,
    navigateToForecast: () -> Unit
) {

    ConstraintLayout {

        val (textHourly, textWeekly, hourlyRow) = createRefs()

        // Hourly text
        Text(
            text = "Today",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = dimensions.forecastTextSize
            ),
            modifier = Modifier
                .constrainAs(textHourly) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .padding(start = 8.dp)
        )

        // Next 7 days forecast link
        Text(
            text = "Next 7 Days",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = dimensions.forecastTextSize
            ),
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .constrainAs(textWeekly) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .padding(end = 8.dp)
                .clickable {
                    navigateToForecast.invoke()
                }
        )

        // Hourly horizontal list
        LazyRow(
            modifier = Modifier
                .constrainAs(hourlyRow) {
                    top.linkTo(textHourly.bottom)
                    top.linkTo(textWeekly.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(0.dp, 0.dp, 0.dp, 2.dp)
                .fillMaxWidth()
        ) {
            items(hourly) { item ->
                HourlyItem(
                    hourly = item,
                    timeUnit = preferenceUnits.time,
                    temperatureUnit = preferenceUnits.temperature
                )
            }
        }

    }

}