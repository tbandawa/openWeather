package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailGrid() {
    Column(
        modifier = Modifier
            .padding(0.dp, 35.dp, 0.dp, 0.dp)
            .width(IntrinsicSize.Max)
    ) {
        Row {
            DetailItem()
            VerticalDivider()
            DetailItem()
            VerticalDivider()
            DetailItem()
        }
        HorizontalDivider()
        Row {
            DetailItem()
            VerticalDivider()
            DetailItem()
            VerticalDivider()
            DetailItem()
        }
        HorizontalDivider()
        Row {
            DetailItem()
            VerticalDivider()
            DetailItem()
            VerticalDivider()
            DetailItem()
        }
    }
}