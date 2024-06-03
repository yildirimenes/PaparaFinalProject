package com.enons.paparaproject.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.enons.paparaproject.utils.AppConstant.SLIDER_ONE
import com.enons.paparaproject.utils.AppConstant.SLIDER_THREE
import com.enons.paparaproject.utils.AppConstant.SLIDER_TWO
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun ImageSliders(modifier: Modifier = Modifier) {
    val imageUrls = listOf(
        SLIDER_ONE,
        SLIDER_TWO,
        SLIDER_THREE
    )
    ImageSlider(
        imageUrls = imageUrls,
        modifier = modifier
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(imageUrls: List<String>, modifier: Modifier = Modifier) {
    val pageCount = imageUrls.size
    val pagerState = rememberPagerState()

    Column(
        modifier = modifier
    ) {
        HorizontalPager(
            modifier = Modifier
                .weight(3f),
            count = pageCount,
            state = pagerState
        ) { page ->
            ImageItem(imageUrl = imageUrls[page])
        }

        LazyRow(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center
        ) {
            items(pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                )
            }
        }
    }
}

@Composable
fun ImageItem(imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(300.dp)
            .shadow(8.dp, shape = ShapeDefaults.Small),
    )
}
