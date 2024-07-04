package com.enons.paparaproject.presentation.screens.recipePage.viewmodel

sealed class RecipeViewIntent {
    object LoadFirstRecipe : RecipeViewIntent()
    data class SearchRecipes(val query: String) : RecipeViewIntent()

}