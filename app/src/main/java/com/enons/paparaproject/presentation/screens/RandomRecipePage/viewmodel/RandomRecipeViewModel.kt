package com.enons.paparaproject.presentation.screens.RandomRecipePage.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enons.paparaproject.data.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RandomRecipeViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {

    private val _state = mutableStateOf<RandomRecipeViewState>(RandomRecipeViewState.Loading)
    val state: State<RandomRecipeViewState> = _state

    init {
        loadRandomRecipe()
    }

    fun loadRandomRecipe() {
        viewModelScope.launch {
            try {
                _state.value = RandomRecipeViewState.Success(
                    mealRepository.getRandomRecipe()
                )
            } catch (e: Exception) {
                _state.value = RandomRecipeViewState.Error("Error fetching random recipe")
            }
        }
    }
}
