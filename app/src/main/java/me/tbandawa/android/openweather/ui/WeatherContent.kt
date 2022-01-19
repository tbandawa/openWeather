package me.tbandawa.android.openweather.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
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
import me.tbandawa.android.openweather.extensions.toTemperature
import me.tbandawa.android.openweather.ui.components.BottomRecycler
import me.tbandawa.android.openweather.ui.components.DetailGrid
import me.tbandawa.android.openweather.ui.components.MainToolBar
import openweather.data.local.PreferenceUnits
import openweather.domain.models.Current
import openweather.domain.models.OneCall
import java.util.*

@ExperimentalAnimationApi
@Composable
fun WeatherContent(
    oneCall: OneCall,
    preferenceUnits: PreferenceUnits
) {

    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = { MainToolBar() }
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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(0.dp, 35.dp, 0.dp, 0.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.weather),
                            contentDescription = null,
                            modifier = Modifier
                                .size(200.dp, 200.dp)
                        )
                        Text(
                            text = oneCall.current?.temp!!.toTemperature(preferenceUnits.temperature),
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 32.sp
                            )
                        )
                        Text(
                            text = oneCall.current!!.weather?.get(0)!!.description!!.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            },
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp
                            )
                        )
                        DetailGrid(oneCall.current!!, preferenceUnits)
                    }
                }
                Column(
                    modifier = Modifier
                        .constrainAs(bottomLayout) {
                            bottom.linkTo(parent.bottom)
                        }
                        .fillMaxWidth()
                ) {
                    BottomRecycler(oneCall.hourly!!, preferenceUnits)
                }
            }
        }
    }

}