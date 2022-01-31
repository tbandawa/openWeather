package me.tbandawa.android.openweather.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Size (
    weatherIconPadding: Dp,
    weatherIconSize: Dp,
    hourlyIconSize: Dp,
    unitIconSize: Dp,
    hourlyItemWidth: Dp,
    detailItemHeight: Dp,
    detailIconSize: Dp
)

val sw360dp = Size(
    weatherIconPadding = 20.dp,
    weatherIconSize = 100.dp,
    hourlyIconSize = 40.dp,
    unitIconSize = 20.dp,
    hourlyItemWidth = 55.dp,
    detailItemHeight = 47.dp,
    detailIconSize = 20.dp
)

val sw480dp = Size(
    weatherIconPadding = 25.dp,
    weatherIconSize = 150.dp,
    hourlyIconSize = 40.dp,
    unitIconSize = 25.dp,
    hourlyItemWidth = 55.dp,
    detailItemHeight = 50.dp,
    detailIconSize = 25.dp
)

val large = Size(
    weatherIconPadding = 35.dp,
    weatherIconSize = 200.dp,
    hourlyIconSize = 55.dp,
    unitIconSize = 30.dp,
    hourlyItemWidth = 70.dp,
    detailItemHeight = 57.dp,
    detailIconSize = 30.dp
)