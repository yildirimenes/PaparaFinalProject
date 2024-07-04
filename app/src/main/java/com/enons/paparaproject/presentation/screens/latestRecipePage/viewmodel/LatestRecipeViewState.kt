package com.enons.paparaproject.presentation.screens.latestRecipePage.viewmodel

import com.enons.paparaproject.data.remote.dto.Meal

sealed class LatestRecipeViewState {
    object Loading : LatestRecipeViewState()
    data class Success(val recipe: List<Meal>) : LatestRecipeViewState()
    data class Error(val message: String) : LatestRecipeViewState()
}