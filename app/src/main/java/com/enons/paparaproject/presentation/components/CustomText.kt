package com.enons.paparaproject.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    text: String,
    padding: Int
) {
    Text(
        text = text,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Serif,
        fontSize = 28.sp,
        modifier = Modifier.padding(padding.dp)
    )
}


