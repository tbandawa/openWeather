package me.tbandawa.android.openweather.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

interface Dimensions {
    val weatherIconPadding: Dp
    val weatherIconSize: Dp
    val hourlyIconSize: Dp
    val unitIconSize: Dp
    val hourlyItemWidth: Dp
    val detailItemHeight: Dp
    val detailTextPadding: Dp
    val detailTextWidth: Dp
    val detailTextSize: TextUnit
    val detailIconSize: Dp
}

data class Sw360dp(
    override val weatherIconPadding: Dp = 35.dp,
    override val weatherIconSize: Dp = 100.dp,
    override val hourlyIconSize: Dp = 40.dp,
    override val unitIconSize: Dp = 20.dp,
    override val hourlyItemWidth: Dp = 60.dp,
    override val detailItemHeight: Dp = 45.dp,
    override val detailTextPadding: Dp = 2.dp,
    override val detailTextWidth: Dp = 70.dp,
    override val detailTextSize: TextUnit = 13.sp,
    override val detailIconSize: Dp = 20.dp
): Dimensions

data class Sw480dp(
    override val weatherIconPadding: Dp = 45.dp,
    override val weatherIconSize: Dp = 150.dp,
    override val hourlyIconSize: Dp = 45.dp,
    override val unitIconSize: Dp = 25.dp,
    override val hourlyItemWidth: Dp = 75.dp,
    override val detailItemHeight: Dp = 45.dp,
    override val detailTextPadding: Dp = 4.dp,
    override val detailTextWidth: Dp = 85.dp,
    override val detailTextSize: TextUnit = 13.sp,
    override val detailIconSize: Dp = 25.dp
): Dimensions

data class Large(
    override val weatherIconPadding: Dp = 35.dp,
    override val weatherIconSize: Dp = 200.dp,
    override val hourlyIconSize: Dp = 55.dp,
    override val unitIconSize: Dp = 30.dp,
    override val hourlyItemWidth: Dp = 70.dp,
    override val detailItemHeight: Dp = 57.dp,
    override val detailTextPadding: Dp = 6.dp,
    override val detailTextWidth: Dp = 80.dp,
    override val detailTextSize: TextUnit = 15.sp,
    override val detailIconSize: Dp = 30.dp
): Dimensions

val sw360dpDimensions = compositionLocalOf { Sw360dp() }
val sw480dpDimensions = compositionLocalOf { Sw480dp() }
val largeDimensions = compositionLocalOf { Large() }
