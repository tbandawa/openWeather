package me.tbandawa.android.openweather.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.tbandawa.android.openweather.R

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