package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import me.tbandawa.android.openweather.ui.theme.dimensions

@Composable
fun DetailItem(
    painter: Painter,
    title: String,
    value: String
) {
    ConstraintLayout(
        modifier = Modifier
            .background(color = Color.White)
            .height(dimensions.detailItemHeight)
            .padding(5.dp, 10.dp, 5.dp, 0.dp)
    ) {
        val (detailIcon, detailTitle, detailValue) = createRefs()
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(detailIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .size(dimensions.unitIconSize, dimensions.unitIconSize)
                .padding(end = 4.dp)
        )
        Text(
            text = title,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = dimensions.detailTextSize
            ),
            modifier = Modifier
                .constrainAs(detailTitle) {
                    start.linkTo(detailIcon.end)
                    top.linkTo(parent.top)
                }
                .width(dimensions.detailTextWidth)
                .padding(top = dimensions.detailTextPadding)
        )
        Text(
            text = value,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            ),
            modifier = Modifier
                .constrainAs(detailValue) {
                    start.linkTo(detailIcon.end)
                    top.linkTo(detailTitle.bottom)
                }
        )
    }
}