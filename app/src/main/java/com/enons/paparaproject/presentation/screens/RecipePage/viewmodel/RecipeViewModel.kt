package com.enons.paparaproject.presentation.screens.RecipePage.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enons.paparaproject.data.local.model.MealEntity
import com.enons.paparaproject.data.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val mealRepository: MealRepository
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
                _state.value = RecipeViewState.Success(
                    mealRepository.getFirstRecipe()
                )
            } catch (e: Exception) {
                _state.value = RecipeViewState.Error("Error fetching recipe")
            }
        }
    }

    private fun searchRecipe(query: String) {
        viewModelScope.launch {
            _state.value = RecipeViewState.Loading
            try {
                _state.value = RecipeViewState.Success(
                    mealRepository.searchRecipe(query)
                )
            } catch (e: Exception) {
                _state.value = RecipeViewState.Error("Error fetching recipes")
            }
        }
    }

    fun insertMessage(message: MealEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            mealRepository.insertRecipe(message)
        }
    }

    fun deleteMessage(message: MealEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            mealRepository.deleteRecipe(message)
        }
    }

}