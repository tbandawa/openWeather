package me.tbandawa.android.openweather.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import me.tbandawa.android.openweather.R
import me.tbandawa.android.openweather.ui.components.HorizontalDivider
import me.tbandawa.android.openweather.ui.components.SettingsToolBar
import me.tbandawa.android.openweather.ui.components.UnitChip
import me.tbandawa.android.openweather.ui.theme.orientation
import openweather.data.local.PreferenceHelper

@ExperimentalMaterialApi
@Composable
fun SettingsContent(
    preferenceHelper: PreferenceHelper,
    navigateUp: () -> Unit
) {

    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:tonderaibandawa@gmail.com")
        putExtra(Intent.EXTRA_SUBJECT, "Feedback - open Radio")
    }

    preferenceHelper.observeAsState(preferenceHelper.get()).value
    val preferenceUnits = preferenceHelper.observeAsState(preferenceHelper.get())

    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = { SettingsToolBar(navigateUp) }
        ) {

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

                    // Units text
                    Text(
                        text = "Units",
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

                    // Temperature units settings
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = when (orientation) {
                                    1 -> {
                                        20.dp
                                    }
                                    2 -> {
                                        5.dp
                                    }
                                    else -> {
                                        20.dp
                                    }
                                }
                            ),
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
                        Surface(
                            color = Color.LightGray,
                            contentColor = Color.LightGray,
                            shape = CircleShape,
                            modifier = Modifier.padding(1.dp)
                        ) {
                            Row {
                                UnitChip(preferenceUnits.value, preferenceHelper::put, "°C")
                                UnitChip(preferenceUnits.value, preferenceHelper::put, "°F")
                            }
                        }
                    }

                    // Wind speed units settings
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = when (orientation) {
                                    1 -> {
                                        20.dp
                                    }
                                    2 -> {
                                        5.dp
                                    }
                                    else -> {
                                        20.dp
                                    }
                                }
                            ),
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
                        Surface(
                            color = Color.LightGray,
                            contentColor = Color.LightGray,
                            shape = CircleShape,
                            modifier = Modifier.padding(1.dp)
                        ) {
                            Row {
                                UnitChip(preferenceUnits.value, preferenceHelper::put, text = "m/s")
                                UnitChip(preferenceUnits.value, preferenceHelper::put, text = "km/h")
                                UnitChip(preferenceUnits.value, preferenceHelper::put, text = "mph")
                            }
                        }
                    }

                    // Pressure units settings
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = when (orientation) {
                                    1 -> {
                                        20.dp
                                    }
                                    2 -> {
                                        5.dp
                                    }
                                    else -> {
                                        20.dp
                                    }
                                }
                            ),
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
                        Surface(
                            color = Color.LightGray,
                            contentColor = Color.LightGray,
                            shape = CircleShape,
                            modifier = Modifier.padding(1.dp)
                        ) {
                            Row {
                                UnitChip(preferenceUnits.value, preferenceHelper::put, text = "hPa")
                                UnitChip(preferenceUnits.value, preferenceHelper::put, text = "inHg")
                            }
                        }
                    }

                    // Time format units settings
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = when (orientation) {
                                    1 -> {
                                        20.dp
                                    }
                                    2 -> {
                                        5.dp
                                    }
                                    else -> {
                                        20.dp
                                    }
                                },
                                bottom = when (orientation) {
                                    1 -> {
                                        45.dp
                                    }
                                    2 -> {
                                        5.dp
                                    }
                                    else -> {
                                        45.dp
                                    }
                                }

                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Time format",
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        )
                        Surface(
                            color = Color.LightGray,
                            contentColor = Color.LightGray,
                            shape = CircleShape,
                            modifier = Modifier.padding(1.dp)
                        ) {
                            Row {
                                UnitChip(preferenceUnits.value, preferenceHelper::put, text = "12-hour")
                                UnitChip(preferenceUnits.value, preferenceHelper::put, text = "24-hour")
                            }
                        }
                    }

                    // Horizontal divider
                    HorizontalDivider()

                    when(orientation) {
                        1 -> {
                            // Icons source row
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
                                    textDecoration = TextDecoration.Underline,
                                    modifier = Modifier
                                        .clickable {
                                            uriHandler.openUri("https://icons8.com/")
                                        }
                                )
                            }

                            // Github link row
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
                                    textDecoration = TextDecoration.Underline,
                                    modifier = Modifier
                                        .clickable {
                                            uriHandler.openUri("https://github.com/tbandawa/openWeather")
                                        }
                                )
                            }

                            // Email link row
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
                                    textDecoration = TextDecoration.Underline,
                                    modifier = Modifier
                                        .clickable {
                                            context.startActivity(Intent.createChooser(intent, "Send Feedback"))
                                        }
                                )
                            }
                        }
                        2 -> {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(1f),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                // Icons source row
                                Row(
                                    modifier = Modifier
                                        .height(35.dp)
                                        .padding(end = 15.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.svg_repo),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(20.dp)
                                            .padding(end = 8.dp)
                                    )
                                    Text(
                                        text = "Icons source",
                                        style = TextStyle(
                                            color = Color.Black,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 16.sp,
                                        ),
                                        textDecoration = TextDecoration.Underline,
                                        modifier = Modifier
                                            .clickable {
                                                uriHandler.openUri("https://icons8.com/")
                                            }
                                    )
                                }

                                // Github link row
                                Row(
                                    modifier = Modifier
                                        .height(30.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.github),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(20.dp)
                                            .padding(end = 8.dp)
                                    )
                                    Text(
                                        text = "Source code",
                                        style = TextStyle(
                                            color = Color.Black,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 16.sp,
                                        ),
                                        textDecoration = TextDecoration.Underline,
                                        modifier = Modifier
                                            .clickable {
                                                uriHandler.openUri("https://github.com/tbandawa/openWeather")
                                            }
                                    )
                                }

                                // Email link row
                                Row(
                                    modifier = Modifier
                                        .height(30.dp)
                                        .padding(start = 15.dp),
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
                                        textDecoration = TextDecoration.Underline,
                                        modifier = Modifier
                                            .clickable {
                                                context.startActivity(Intent.createChooser(intent, "Send Feedback"))
                                            }
                                    )
                                }
                            }
                        }
                    }

                }

                // Powered by OpenWeather text
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

    }

}