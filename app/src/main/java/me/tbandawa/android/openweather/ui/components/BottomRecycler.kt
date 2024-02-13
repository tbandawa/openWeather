package me.tbandawa.android.openweather.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

        val (textHourly, hourlyRow, buttonWeekly) = createRefs()

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

        // Hourly horizontal list
        LazyRow(
            modifier = Modifier
                .constrainAs(hourlyRow) {
                    top.linkTo(textHourly.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(buttonWeekly.top)
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

        // Next 7 days forecast button
        Surface(
            color = Color.Black,
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .constrainAs(buttonWeekly) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(end = 16.dp),
            onClick = {
                navigateToForecast.invoke()
            }
        ) {
            Text(
                text = "Next 7 Days",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }
    }
}