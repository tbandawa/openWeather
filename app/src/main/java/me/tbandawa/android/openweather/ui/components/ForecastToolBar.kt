package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.openweather.R

@OptIn(ExperimentalMaterial3Api::class)
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
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .padding(10.dp)
            .shadow(
                elevation = 5.dp,
                spotColor = Color.DarkGray,
                shape = RoundedCornerShape(10.dp)
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