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
    val forecastTextSize: TextUnit
    val detailItemHeight: Dp
    val detailTextPadding: Dp
    val detailTextWidth: Dp
    val detailTextSize: TextUnit
    val valueTextSize: TextUnit
    val detailIconSize: Dp
    val moreItemHeight: Dp
    val moreIconSize: Dp
    val moreTextPadding: Dp
    val moreTextWidth: Dp
    val moreTextSize: TextUnit
    val weatherToolBarTitleTextSize: TextUnit
    val weatherToolBarDateTextSize: TextUnit
    val weatherTemperatureTextSize: TextUnit
    val weatherDescriptionTextSize: TextUnit
    val toolBarTitleTextSize: TextUnit
}

data class Sw360dp(
    override val weatherIconPadding: Dp = 35.dp,
    override val weatherIconSize: Dp = 100.dp,
    override val hourlyIconSize: Dp = 35.dp,
    override val unitIconSize: Dp = 20.dp,
    override val hourlyItemWidth: Dp = 60.dp,
    override val forecastTextSize: TextUnit = 14.sp,
    override val detailItemHeight: Dp = 45.dp,
    override val detailTextPadding: Dp = 2.dp,
    override val detailTextWidth: Dp = 70.dp,
    override val detailTextSize: TextUnit = 11.sp,
    override val valueTextSize: TextUnit = 10.sp,
    override val detailIconSize: Dp = 20.dp,
    override val moreItemHeight: Dp = 45.dp,
    override val moreIconSize: Dp = 20.dp,
    override val moreTextPadding: Dp = 2.dp,
    override val moreTextWidth: Dp = 80.dp,
    override val moreTextSize: TextUnit = 13.sp,
    override val weatherToolBarTitleTextSize: TextUnit = 16.sp,
    override val weatherToolBarDateTextSize: TextUnit = 12.sp,
    override val weatherTemperatureTextSize: TextUnit = 28.sp,
    override val weatherDescriptionTextSize: TextUnit = 16.sp,
    override val toolBarTitleTextSize: TextUnit = 18.sp
): Dimensions

data class Sw480dp(
    override val weatherIconPadding: Dp = 15.dp,
    override val weatherIconSize: Dp = 150.dp,
    override val hourlyIconSize: Dp = 40.dp,
    override val unitIconSize: Dp = 25.dp,
    override val hourlyItemWidth: Dp = 75.dp,
    override val forecastTextSize: TextUnit = 15.sp,
    override val detailItemHeight: Dp = 45.dp,
    override val detailTextPadding: Dp = 4.dp,
    override val detailTextWidth: Dp = 85.dp,
    override val detailTextSize: TextUnit = 12.sp,
    override val valueTextSize: TextUnit = 11.sp,
    override val detailIconSize: Dp = 25.dp,
    override val moreItemHeight: Dp = 45.dp,
    override val moreIconSize: Dp = 25.dp,
    override val moreTextPadding: Dp = 4.dp,
    override val moreTextWidth: Dp = 90.dp,
    override val moreTextSize: TextUnit = 13.sp,
    override val weatherToolBarTitleTextSize: TextUnit = 16.sp,
    override val weatherToolBarDateTextSize: TextUnit = 12.sp,
    override val weatherTemperatureTextSize: TextUnit = 30.sp,
    override val weatherDescriptionTextSize: TextUnit = 18.sp,
    override val toolBarTitleTextSize: TextUnit = 18.sp
): Dimensions

data class Large(
    override val weatherIconPadding: Dp = 25.dp,
    override val weatherIconSize: Dp = 200.dp,
    override val hourlyIconSize: Dp = 55.dp,
    override val unitIconSize: Dp = 30.dp,
    override val hourlyItemWidth: Dp = 70.dp,
    override val forecastTextSize: TextUnit = 16.sp,
    override val detailItemHeight: Dp = 57.dp,
    override val detailTextPadding: Dp = 6.dp,
    override val detailTextWidth: Dp = 80.dp,
    override val detailTextSize: TextUnit = 13.sp,
    override val valueTextSize: TextUnit = 12.sp,
    override val detailIconSize: Dp = 30.dp,
    override val moreItemHeight: Dp = 57.dp,
    override val moreIconSize: Dp = 30.dp,
    override val moreTextPadding: Dp = 6.dp,
    override val moreTextWidth: Dp = 85.dp,
    override val moreTextSize: TextUnit = 15.sp,
    override val weatherToolBarTitleTextSize: TextUnit = 16.sp,
    override val weatherToolBarDateTextSize: TextUnit = 12.sp,
    override val weatherTemperatureTextSize: TextUnit = 32.sp,
    override val weatherDescriptionTextSize: TextUnit = 18.sp,
    override val toolBarTitleTextSize: TextUnit = 18.sp
): Dimensions

val LocalSw360dpDimensions = compositionLocalOf { Sw360dp() }
val LocalSw480dpDimensions = compositionLocalOf { Sw480dp() }
val LocalLargeDimensions = compositionLocalOf { Large() }
