package me.tbandawa.android.openweather.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.launch
import me.tbandawa.android.openweather.BuildConfig.*
import me.tbandawa.android.openweather.R
import me.tbandawa.android.openweather.ui.components.HorizontalDivider
import me.tbandawa.android.openweather.ui.components.SettingsToolBar
import me.tbandawa.android.openweather.ui.components.UnitChip
import me.tbandawa.android.openweather.ui.theme.orientation
import openweather.data.local.UnitsPreferencesDataStoreImpl
import openweather.domain.datastore.UnitsPreferencesDataStore
import openweather.domain.models.PreferenceUnits

@Composable
fun SettingsContent(
    unitsPreferencesDataStore: UnitsPreferencesDataStore,
    navigateUp: () -> Unit
) {

    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse(OPEN_WEATHER_AUTHER_EMAIL)
        putExtra(Intent.EXTRA_SUBJECT, "Feedback - open Radio")
    }

    val preferenceUnits = unitsPreferencesDataStore.preferencesUnits.collectAsState(
        PreferenceUnits("°C", "m/s", "hPa", "12-hour")
    ).value

    val coroutineScope = rememberCoroutineScope()

    val updateUnitsPreference: (PreferenceUnits) -> Unit = { preferenceUnits ->
        coroutineScope.launch {
            unitsPreferencesDataStore.savePreferencesUnits(preferenceUnits)
        }
    }

    Surface {
        Scaffold(
            topBar = { SettingsToolBar(navigateUp) },
            containerColor = Color.White
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
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
                            modifier = Modifier
                                .height(35.dp)
                        ) {
                            Row {
                                UnitChip(preferenceUnits, updateUnitsPreference, "°C")
                                UnitChip(preferenceUnits, updateUnitsPreference, "°F")
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
                            modifier = Modifier
                                .height(35.dp)
                        ) {
                            Row {
                                UnitChip(preferenceUnits, updateUnitsPreference, text = "m/s")
                                UnitChip(preferenceUnits, updateUnitsPreference, text = "km/h")
                                UnitChip(preferenceUnits, updateUnitsPreference, text = "mph")
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
                            modifier = Modifier
                                .height(35.dp)
                        ) {
                            Row {
                                UnitChip(preferenceUnits, updateUnitsPreference, text = "hPa")
                                UnitChip(preferenceUnits, updateUnitsPreference, text = "inHg")
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
                            modifier = Modifier
                                .height(35.dp)
                        ) {
                            Row {
                                UnitChip(preferenceUnits, updateUnitsPreference, text = "12-hour")
                                UnitChip(preferenceUnits, updateUnitsPreference, text = "24-hour")
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
                                            uriHandler.openUri(OPEN_WEATHER_ICONS_SOURCE_URL)
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
                                            uriHandler.openUri(OPEN_WEATHER_SOURCE_CODE_URL)
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

@Preview(showBackground = true)
@Composable
fun SettingsContentPreview() {
    SettingsContent(
        unitsPreferencesDataStore = UnitsPreferencesDataStoreImpl(LocalContext.current),
        navigateUp = { }
    )
}