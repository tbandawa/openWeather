package me.tbandawa.android.openweather.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import me.tbandawa.android.openweather.MainViewModel
import me.tbandawa.android.openweather.ui.components.ForecastItem
import me.tbandawa.android.openweather.ui.components.ForecastToolBar
import openweather.data.local.PreferenceHelper

@ExperimentalAnimationApi
@Composable
fun ForecastContent(
    preferenceHelper: PreferenceHelper,
    viewModel: MainViewModel,
    navigateUp: () -> Unit
){

    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = { ForecastToolBar(navigateUp) }
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val (topLayout, recyclerLayout) = createRefs()
                Column(
                    modifier = Modifier
                        .constrainAs(topLayout) {
                            top.linkTo(parent.top)
                        }
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                ) {
                    Text(
                        text = "Next 7 Days",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        ),
                        modifier = Modifier
                            .padding(16.dp, 25.dp, 16.dp, 15.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .constrainAs(recyclerLayout) {
                            top.linkTo(topLayout.bottom)
                        }
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(5) { index ->
                            ForecastItem()
                        }
                    }
                }
            }
        }
    }

}