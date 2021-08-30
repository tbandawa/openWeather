package me.tbandawa.android.openweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenWeatherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = { ToolBar() }
                    ) {
                        MainContent()
                    }
                }
            }
        }
    }
}

@Composable
fun ToolBar() {
    TopAppBar(
        title = {
            Column() {
                Text(text = "AppBar")
                Text(text = "AppBar", style = TextStyle(fontSize = 12.sp))
            }
        },
        backgroundColor = Color.White,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp),
                    tint = Color.Black
                )
            }
        }
    )
}

@Composable
fun MainContent() {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
    ) {
        val (topLayout, bottomLayout) = createRefs()
        Column(
            modifier = Modifier
                .constrainAs(topLayout) {
                    top.linkTo(parent.top)
                }
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) {

        }
        Column(
            modifier = Modifier
                .constrainAs(bottomLayout) {
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OpenWeatherTheme {
        MainContent()
    }
}