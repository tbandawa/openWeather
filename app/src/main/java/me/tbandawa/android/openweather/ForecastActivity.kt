package me.tbandawa.android.openweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme

class ForecastActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenWeatherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = { ForecastToolBar() }
                    ) {
                        ForecastContent()
                    }
                }
            }
        }
    }
}

@Composable
fun ForecastToolBar() {
    TopAppBar(
        title = {
            Text(text = "Johannesburg, South Africa")
        },
        navigationIcon = {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp),
                    tint = Color.Black
                )
            }
        },
        backgroundColor = Color.White,
        elevation = 0.dp
    )
}

@Composable
fun ForecastContent() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (topLayout, recyclerLayout) = createRefs()
        Column(
            modifier = Modifier
                .constrainAs(topLayout) {
                    top.linkTo(parent.top)
                }
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {
            Text(
                text = "Next 7 Days",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                ),
                modifier = Modifier
                    .padding(16.dp, 25.dp, 16.dp, 15.dp)
            )
        }
        Column(
            modifier = Modifier
                .constrainAs(recyclerLayout) {
                    top.linkTo(topLayout.bottom)
                }
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

        }
    }
}

@Composable
fun ForecastItem() {
    ConstraintLayout(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .padding(0.dp, 5.dp, 0.dp, 5.dp)
    ) {
        val (visibleLayout, moreLayout) = createRefs()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(visibleLayout) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(10.dp, 0.dp, 10.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sun",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Text(
                text = "22°C",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .padding(16.dp, 0.dp, 0.dp, 0.dp)
            )
            Text(
                text = "22°C",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .padding(8.dp, 0.dp, 0.dp, 0.dp)
            )
            Image(
                painter = painterResource(R.drawable.weather),
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp, 40.dp)
                    .padding(16.dp, 0.dp, 8.dp, 0.dp)
            )
            Text(
                text = "Partly Cloudy",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .padding(16.dp, 0.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .constrainAs(moreLayout) {
                    start.linkTo(parent.start)
                    top.linkTo(visibleLayout.bottom)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(0.dp, 0.dp, 0.dp, 0.dp)
        ) {
            Row {
                MoreItem()
                MoreItem()
                MoreItem()
            }
            Row {
                MoreItem()
                MoreItem()
                MoreItem()
            }
        }
    }
}

@Composable
fun MoreItem() {
    ConstraintLayout(
        modifier = Modifier
            .background(color = Color.White)
            .height(57.dp)
            .padding(5.dp, 5.dp, 5.dp, 5.dp)
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
                .size(25.dp, 30.dp)
                .padding(0.dp, 0.dp, 8.dp, 0.dp)
        )
        Text(
            text = "Wind Speed",
            style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
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

@Preview(showBackground = true)
@Composable
fun ForecastPreview() {
    OpenWeatherTheme {
        ForecastItem()
    }
}