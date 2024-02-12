package me.tbandawa.android.openweather.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalPermissionsApi
@Composable
fun PermissionContent(
    launchPermissionRequest: () -> Unit
){

    Surface(color = Color.White) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, bottom = 50.dp, end = 20.dp)
        ) {

            val context = LocalContext.current

            val (titleLayout, descriptionLayout, allowButton, cancelButton) = createRefs()

            // Permission title text
            Text(
                text = "Location Access",
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

            // Permission description text
            Text(
                text = "Allow openWeather to access your location",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                ),
                modifier = Modifier
                    .constrainAs(descriptionLayout) {
                        start.linkTo(parent.start)
                        bottom.linkTo(allowButton.top)
                        bottom.linkTo(cancelButton.top)
                    }
                    .width(300.dp)
                    .height(IntrinsicSize.Max)
                    .padding(bottom = 10.dp)
            )

            // Show permission dialog
            Button(
                onClick = { launchPermissionRequest.invoke() },
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .constrainAs(allowButton) {
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(end = 5.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Allow")
            }

            // Exit button
            Button(
                onClick = {
                    (context as ComponentActivity).finish()
                },
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .constrainAs(cancelButton) {
                        start.linkTo(allowButton.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(start = 5.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Exit")
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Preview(showBackground = true)
@Composable
fun PermissionContentPreview() {
    PermissionContent(){ }
}