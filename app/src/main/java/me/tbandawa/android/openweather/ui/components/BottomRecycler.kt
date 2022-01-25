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
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import openweather.data.local.PreferenceUnits
import openweather.domain.models.Hourly

@ExperimentalAnimationApi
@Composable
fun BottomRecycler(
    hourly: List<Hourly>,
    preferenceUnits: PreferenceUnits,
    navigateToForecast: () -> Unit
) {

    ConstraintLayout {
        val (textHourly, textWeekly, hourlyRow) = createRefs()
        Text(
            text = "Today",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .constrainAs(textHourly) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .padding(8.dp, 8.dp, 0.dp, 8.dp)
        )
        LazyRow(
            modifier = Modifier
                .constrainAs(hourlyRow) {
                    top.linkTo(textHourly.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(textWeekly.top)
                }
                .padding(0.dp, 0.dp, 0.dp, 8.dp)
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
        Text(
            text = "Next 7 Days",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .constrainAs(textWeekly) {
                    end.linkTo(parent.end)
                    top.linkTo(hourlyRow.bottom)
                    bottom.linkTo(parent.bottom)
                }
                .padding(top = 4.dp, end = 8.dp, bottom = 10.dp)
                .clickable {
                    navigateToForecast.invoke()
                }
        )
    }
}