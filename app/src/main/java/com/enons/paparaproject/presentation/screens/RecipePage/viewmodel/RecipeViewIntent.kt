package com.enons.paparaproject.presentation.screens.RecipePage.viewmodel

sealed class RecipeViewIntent {
    object LoadFirstRecipe : RecipeViewIntent()
    data class SearchRecipes(val query: String) : RecipeViewIntent()

}