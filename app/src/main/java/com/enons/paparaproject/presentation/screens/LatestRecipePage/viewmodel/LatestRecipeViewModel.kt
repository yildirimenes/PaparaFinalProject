package com.enons.paparaproject.presentation.screens.LatestRecipePage.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enons.paparaproject.data.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestRecipeViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {

    private val _state = mutableStateOf<LatestRecipeViewState>(LatestRecipeViewState.Loading)
    val state: State<LatestRecipeViewState> = _state

    init {
        loadLatestRecipe()
    }

    fun loadLatestRecipe() {
        viewModelScope.launch {
            try {
                _state.value = LatestRecipeViewState.Success(
                    mealRepository.getLatestRecipe()
                )
            } catch (e: Exception) {
                _state.value = LatestRecipeViewState.Error("Error fetching random recipe")
            }
        }
    }
}