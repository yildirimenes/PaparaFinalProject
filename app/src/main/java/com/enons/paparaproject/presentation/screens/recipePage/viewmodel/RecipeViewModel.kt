package com.enons.paparaproject.presentation.screens.recipePage.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enons.paparaproject.data.local.model.MealEntity
import com.enons.paparaproject.domain.useCase.network.DeleteRecipeUseCase
import com.enons.paparaproject.domain.useCase.network.InsertRecipeUseCase
import com.enons.paparaproject.domain.useCase.network.LoadFirstRecipeUseCase
import com.enons.paparaproject.domain.useCase.network.SearchRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val loadFirstRecipeUseCase: LoadFirstRecipeUseCase,
    private val searchRecipesUseCase: SearchRecipesUseCase,
    private val insertRecipeUseCase: InsertRecipeUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase
) : ViewModel() {

    private val _state = mutableStateOf<RecipeViewState>(RecipeViewState.Loading)
    val state: State<RecipeViewState> = _state

    fun processIntent(intent: RecipeViewIntent) {
        when (intent) {
            is RecipeViewIntent.LoadFirstRecipe -> loadFirstRecipe()
            is RecipeViewIntent.SearchRecipes -> searchRecipe(intent.query)
        }
    }

    private fun loadFirstRecipe() {
        viewModelScope.launch {
            try {
                val recipe = loadFirstRecipeUseCase()
                _state.value = RecipeViewState.Success(recipe)
            } catch (e: Exception) {
                _state.value = RecipeViewState.Error("Error fetching recipe")
            }
        }
    }

    private fun searchRecipe(query: String) {
        viewModelScope.launch {
            _state.value = RecipeViewState.Loading
            try {
                val recipes = searchRecipesUseCase(query)
                _state.value = RecipeViewState.Success(recipes)
            } catch (e: Exception) {
                _state.value = RecipeViewState.Error("Error fetching recipes")
            }
        }
    }

    fun insertRecipe(recipe: MealEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            insertRecipeUseCase(recipe)
        }
    }

    fun deleteRecipe(recipe: MealEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteRecipeUseCase(recipe)
        }
    }
}
