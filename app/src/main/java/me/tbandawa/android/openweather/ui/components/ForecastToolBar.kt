package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.tbandawa.android.openweather.R

@Composable
fun ForecastToolBar(
    location: String,
    navigateUp: () -> Unit
) {

    TopAppBar(
        title = {
            Text(text = location)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navigateUp.invoke()
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