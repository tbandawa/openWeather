package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.tbandawa.android.openweather.R
import me.tbandawa.android.openweather.ui.theme.dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastToolBar(
    location: String,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = location,
                style = TextStyle(
                    fontSize = dimensions.toolBarTitleTextSize,
                    fontWeight = FontWeight.Bold
                )
            )
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
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ForecastToolBarPreview() {
    ForecastToolBar(
        location = "Home Toolbar",
        navigateUp = { }
    )
}