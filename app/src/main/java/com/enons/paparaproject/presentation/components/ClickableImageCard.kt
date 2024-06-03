package com.enons.paparaproject.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun ClickableImageCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Column {
            CoilImage(
                imageModel = { imageUrl },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 10f), // Resmin oranını ayarlayabilirsiniz
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillBounds,
                )
            )
            Text(
                text = text,
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif,
                color = Color.Gray,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}
