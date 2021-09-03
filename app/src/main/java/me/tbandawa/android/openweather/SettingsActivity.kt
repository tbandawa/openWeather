package me.tbandawa.android.openweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.app.ActivityCompat
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme

@ExperimentalAnimationApi
class SettingsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenWeatherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = { SettingsToolBar() }
                    ) {
                        SettingsContent()
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsToolBar() {

    val context = LocalContext.current
    TopAppBar(
        title = {
            Text(text = "Settings")
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    (context as ComponentActivity).finish()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp),
                    tint = Color.Black
                )
            }
        },
        backgroundColor = Color.White,
        elevation = 0.dp
    )
}

@Composable
fun SettingsContent() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp, 16.dp, 0.dp)
    ) {
        val (contentLayout, bottomLayout) = createRefs()
        Column(
            modifier = Modifier
                .constrainAs(contentLayout) {
                    top.linkTo(parent.top)
                }
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {

            Text(
                text = "Units",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                ),
                modifier = Modifier
                    .padding(0.dp, 16.dp, 0.dp, 8.dp)
            )

            HorizontalDivider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Temperature",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Row {
                    Chip(selected = true, text = "°C")
                    Chip(selected = false, text = "°F")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Wind speed",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Row {
                    Chip(selected = true, text = "m/s")
                    Chip(selected = false, text = "km/h")
                    Chip(selected = false, text = "mph")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Pressure",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Row {
                    Chip(selected = true, text = "hPa")
                    Chip(selected = false, text = "inHg")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Distance",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Row {
                    Chip(selected = true, text = "km")
                    Chip(selected = false, text = "mi")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 45.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Time formart",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Row {
                    Chip(selected = true, text = "24-hour")
                    Chip(selected = false, text = "12-hour")
                }
            }

            HorizontalDivider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .padding(top = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.svg_repo),
                    contentDescription = null,
                    modifier = Modifier
                        .height(30.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "Icons source",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.github),
                    contentDescription = null,
                    modifier = Modifier
                        .height(25.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "Source code",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_email),
                    contentDescription = null,
                    modifier = Modifier
                        .height(30.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "E-mail",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

        }
        Row(
            modifier = Modifier
                .constrainAs(bottomLayout) {
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(0.dp, 0.dp, 0.dp, 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Powered by OpenWeather",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Composable
fun Chip(
    selected: Boolean,
    text: String
) {
    Surface(
        color = when {
            selected -> MaterialTheme.colors.onSurface
            else -> Color.Transparent
        },
        contentColor = when {
            selected -> MaterialTheme.colors.onPrimary
            else -> Color.LightGray
        },
        shape = CircleShape,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    OpenWeatherTheme {
        Scaffold(
            topBar = { SettingsToolBar() }
        ) {
            SettingsContent()
        }
    }
}