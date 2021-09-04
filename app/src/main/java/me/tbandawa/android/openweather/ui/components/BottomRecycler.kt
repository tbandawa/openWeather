package me.tbandawa.android.openweather.ui.components

import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import me.tbandawa.android.openweather.ForecastActivity

@ExperimentalAnimationApi
@Composable
fun BottomRecycler() {

    val context = LocalContext.current

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
        Text(
            text = "Next 7 Days",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .constrainAs(textWeekly) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .padding(0.dp, 8.dp, 8.dp, 8.dp)
                .clickable {
                    context.startActivity(Intent(context, ForecastActivity::class.java))
                }
        )
        LazyRow(
            modifier = Modifier
                .constrainAs(hourlyRow) {
                    top.linkTo(textHourly.bottom)
                    top.linkTo(textWeekly.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(0.dp, 0.dp, 0.dp, 8.dp)
                .fillMaxWidth()
        ) {
            items(16) { index ->
                HourlyItem()
            }
        }
    }
}