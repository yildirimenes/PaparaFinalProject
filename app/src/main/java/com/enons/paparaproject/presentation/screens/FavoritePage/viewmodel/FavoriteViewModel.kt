package com.enons.paparaproject.presentation.screens.FavoritePage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enons.paparaproject.core.ApiResult.ApiResult
import com.enons.paparaproject.data.local.model.MealEntity
import com.enons.paparaproject.data.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: MealRepository) : ViewModel() {

    private val _favoriteMeals = MutableStateFlow<ApiResult<List<MealEntity>>>(ApiResult.Success(emptyList()))
    val favoriteMeals: StateFlow<ApiResult<List<MealEntity>>> = _favoriteMeals

    init {
        getFavoriteMeals()
    }

    private fun getFavoriteMeals() {
        viewModelScope.launch {
            repository.getAllRecipe().collect { result ->
                _favoriteMeals.value = result
            }
        }
    }

    fun removeFromFavorites(mealName: String) {
        viewModelScope.launch {
            val favoriteMealsResult = _favoriteMeals.value
            if (favoriteMealsResult is ApiResult.Success) {
                val meals = favoriteMealsResult.data.toMutableList()
                val mealToRemove = meals.find { it.mealName == mealName }
                mealToRemove?.let {
                    meals.remove(it)
                    _favoriteMeals.value = ApiResult.Success(meals)
                    repository.deleteRecipe(it)
                }
            }
        }
    }
}
