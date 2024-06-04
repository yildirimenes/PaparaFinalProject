package com.enons.paparaproject.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.enons.paparaproject.data.remote.dto.Meal

@Composable
fun RecipesList(recipes: List<Meal>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){
        items(recipes) {
            RecipeListItem(it)
        }
    }
}