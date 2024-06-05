package com.enons.paparaproject.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enons.paparaproject.R

@Composable
fun CustomTitleText(text: String) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(colorResource(id = R.color.color_two), colorResource(id = R.color.color_one))
    )
    Text(
        text = text,

        style = TextStyle(
            fontSize = 36.sp,
            brush = gradientBrush
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp),
        textAlign = TextAlign.Start
    )
}

@Composable
fun CustomSubTitleText(text: String) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(colorResource(id = R.color.color_two), colorResource(id = R.color.color_one))
    )
    Text(
        text = text,

        style = TextStyle(
            fontSize = 24.sp,
            brush = gradientBrush
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp),
        textAlign = TextAlign.Start
    )
}