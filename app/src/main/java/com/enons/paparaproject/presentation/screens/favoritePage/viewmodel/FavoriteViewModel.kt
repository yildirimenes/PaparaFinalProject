package com.enons.paparaproject.presentation.screens.favoritePage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enons.paparaproject.core.apiResult.ApiResult
import com.enons.paparaproject.data.local.model.MealEntity
import com.enons.paparaproject.domain.useCase.database.GetFavoriteMealsUseCase
import com.enons.paparaproject.domain.useCase.database.RemoveFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteMealsUseCase: GetFavoriteMealsUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
) : ViewModel() {

    private val _favoriteMeals = MutableStateFlow<ApiResult<List<MealEntity>>>(ApiResult.Success(emptyList()))
    val favoriteMeals: StateFlow<ApiResult<List<MealEntity>>> = _favoriteMeals

    init {
        getFavoriteMeals()
    }

    private fun getFavoriteMeals() {
        viewModelScope.launch {
            getFavoriteMealsUseCase().collect { result ->
                _favoriteMeals.value = result
            }
        }
    }

    fun removeFromFavorites(mealName: String) {
        viewModelScope.launch {
            val favoriteMealsResult = _favoriteMeals.value
            if (favoriteMealsResult is ApiResult.Success) {
                val updatedFavorites = removeFromFavoritesUseCase(mealName, favoriteMealsResult.data)
                _favoriteMeals.value = updatedFavorites
            }
        }
    }
}
