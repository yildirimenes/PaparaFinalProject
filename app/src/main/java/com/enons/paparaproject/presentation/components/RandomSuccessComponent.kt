package com.enons.paparaproject.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.enons.paparaproject.data.remote.dto.Meal

@Composable
fun RandomSuccessComponent(recipe: List<Meal>) {
    Column {
        Box(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
        )
        RecipesList(recipes = recipe)
    }
}