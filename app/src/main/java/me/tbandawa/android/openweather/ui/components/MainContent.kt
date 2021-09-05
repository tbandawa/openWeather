package me.tbandawa.android.openweather.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import openweather.domain.models.OneCall

@ExperimentalAnimationApi
@Composable
fun MainContent(
    oneCall: OneCall
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (topLayout, bottomLayout) = createRefs()
        Column(
            modifier = Modifier
                .constrainAs(topLayout) {
                    top.linkTo(parent.top)
                }
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            WeatherContent(oneCall.current!!)
        }
        Column(
            modifier = Modifier
                .constrainAs(bottomLayout) {
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth()
        ) {
            BottomRecycler()
        }
    }
}
