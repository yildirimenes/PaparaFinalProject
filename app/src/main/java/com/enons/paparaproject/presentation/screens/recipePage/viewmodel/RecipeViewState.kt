package com.enons.paparaproject.presentation.screens.recipePage.viewmodel

import com.enons.paparaproject.data.remote.dto.Meal

sealed class RecipeViewState {
    object Loading: RecipeViewState()
    data class Success(val recipes: List<Meal>): RecipeViewState()
    data class Error(val message: String): RecipeViewState()
}