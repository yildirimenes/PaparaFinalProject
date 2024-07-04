package com.enons.paparaproject.presentation.screens.randomRecipePage.viewmodel

import com.enons.paparaproject.data.remote.dto.Meal

sealed class RandomRecipeViewState {
    object Loading : RandomRecipeViewState()
    data class Success(val recipe: List<Meal>) : RandomRecipeViewState()
    data class Error(val message: String) : RandomRecipeViewState()
}