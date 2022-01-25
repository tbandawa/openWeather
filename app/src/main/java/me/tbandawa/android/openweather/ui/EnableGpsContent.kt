package me.tbandawa.android.openweather.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalPermissionsApi
@Composable
fun EnableGpsContent() {

    Surface(color = MaterialTheme.colors.background) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, bottom = 50.dp, end = 20.dp)
        ) {

            val context = LocalContext.current

            val (titleLayout, descriptionLayout, cancelButton) = createRefs()

            // Enable location text
            Text(
                text = "Enable location.",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .constrainAs(titleLayout) {
                        bottom.linkTo(descriptionLayout.top)
                        start.linkTo(parent.start)
                    }
                    .height(IntrinsicSize.Min)
                    .padding(0.dp, 0.dp, 0.dp, 10.dp)
            )

            // Location needed explanation text
            Text(
                text = "openWeather needs the location to know where you are.",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                ),
                modifier = Modifier
                    .constrainAs(descriptionLayout) {
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
                    .width(300.dp)
                    .height(IntrinsicSize.Max)
                    .padding(bottom = 10.dp)
            )

        }
    }

}