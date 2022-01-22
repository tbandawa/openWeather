package me.tbandawa.android.openweather.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import me.tbandawa.android.openweather.R

@Composable
fun MoreItem(
    painter: Painter,
    title: String,
    value: String
) {
    ConstraintLayout(
        modifier = Modifier
            .height(57.dp)
            .padding(5.dp, 5.dp, 5.dp, 5.dp)
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
                .size(25.dp, 30.dp)
                .padding(0.dp, 0.dp, 8.dp, 0.dp)
        )
        Text(
            text = title,
            style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 8.sp
            ),
            modifier = Modifier
                .constrainAs(detailTitle) {
                    start.linkTo(detailIcon.end)
                    top.linkTo(parent.top)
                }
                .size(80.dp, 28.dp)
                .padding(0.dp, 8.dp, 0.dp, 0.dp)
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