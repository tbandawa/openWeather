package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import me.tbandawa.android.openweather.R
import me.tbandawa.android.openweather.SettingsViewModel
import openweather.data.local.PreferenceHelper
import openweather.data.local.PreferenceUnits

@ExperimentalMaterialApi
@Composable
fun SettingsContent(
    preferenceUnits: PreferenceUnits,
    setPreference: (PreferenceUnits) -> Unit
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

            TemperatureSettings(preferenceUnits, setPreference)

            SpeedSettings(preferenceUnits, setPreference)

            PressureSettings(preferenceUnits, setPreference)

            DistanceSettings(preferenceUnits, setPreference)

            TimeSettings(preferenceUnits, setPreference)

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