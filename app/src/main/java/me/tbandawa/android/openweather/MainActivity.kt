package me.tbandawa.android.openweather

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenWeatherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = { MainToolBar() }
                    ) {
                        MainContent()
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MainToolBar() {

    val context = LocalContext.current

    TopAppBar(
        title = {
            Column() {
                Text(text = "Johannesburg")
                Text(
                    text = "22, Aug 2021",
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        },
        backgroundColor = Color.White,
        elevation = 0.dp,
        actions = {
            IconButton(
                onClick = {
                    context.startActivity(Intent(context, SettingsActivity::class.java))
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp),
                    tint = Color.Black
                )
            }
        }
    )
}

@ExperimentalAnimationApi
@Composable
fun MainContent() {
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
            WeatherContent()
        }
        Column(
            modifier = Modifier
                .constrainAs(bottomLayout) {
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth()
        ) {
            BottomRecycler()
        }
    }
}

@Composable
fun WeatherContent() {
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
            text = "22°C",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
        )
        Text(
            text = "Partly Cloudy",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        )
        DetailGrid()
    }
}

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

@Composable
fun DetailGrid() {
    Column(
        modifier = Modifier
            .padding(0.dp, 35.dp, 0.dp, 0.dp)
            .width(IntrinsicSize.Max)
    ) {
        Row {
            DetailItem()
            VerticalDivider()
            DetailItem()
            VerticalDivider()
            DetailItem()
        }
        HorizontalDivider()
        Row {
            DetailItem()
            VerticalDivider()
            DetailItem()
            VerticalDivider()
            DetailItem()
        }
        HorizontalDivider()
        Row {
            DetailItem()
            VerticalDivider()
            DetailItem()
            VerticalDivider()
            DetailItem()
        }
    }
}

@Composable
fun HourlyItem() {
    Column(modifier = Modifier
        .width(65.dp)
        .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "8 am",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
        Image(
            painter = painterResource(R.drawable.ic_cloud),
            contentDescription = null,
            modifier = Modifier
                .size(35.dp, 35.dp)
                .padding(0.dp, 5.dp, 0.dp, 5.dp)
        )
        Text(
            text = "22°C",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
        )
    }
}

@Composable
fun DetailItem() {
    ConstraintLayout(
        modifier = Modifier
            .background(color = Color.White)
            .height(57.dp)
            .padding(10.dp, 5.dp, 5.dp, 5.dp)
    ) {
        val (detailIcon, detailTitle, detailValue) = createRefs()
        Image(
            painter = painterResource(R.drawable.ic_cloud),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(detailIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .size(30.dp, 30.dp)
                .padding(0.dp, 0.dp, 8.dp, 0.dp)
        )
        Text(
            text = "Wind Speed",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                lineHeight = 8.sp
            ),
            modifier = Modifier
                .constrainAs(detailTitle) {
                    start.linkTo(detailIcon.end)
                    top.linkTo(parent.top)
                }
                .size(80.dp, 28.dp)
                .padding(0.dp, 8.dp, 0.dp, 0.dp)
        )
        Text(
            text = "22°C",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            ),
            modifier = Modifier
                .constrainAs(detailValue) {
                    start.linkTo(detailIcon.end)
                    top.linkTo(detailTitle.bottom)
                }
        )
    }
}

@Composable
fun VerticalDivider() {
    Spacer(modifier = Modifier
        .width(1.dp)
        .height(57.dp)
        .background(color = Color.LightGray))
}

@Composable
fun HorizontalDivider() {
    Spacer(modifier = Modifier
        .height(1.dp)
        .fillMaxWidth()
        .background(color = Color.LightGray))
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    OpenWeatherTheme {
        MainContent()
    }
}