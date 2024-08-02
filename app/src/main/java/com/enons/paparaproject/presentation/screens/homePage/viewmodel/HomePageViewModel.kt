package com.enons.paparaproject.presentation.screens.homePage.viewmodel

import androidx.lifecycle.ViewModel
import com.enons.paparaproject.core.constants.AppConstant.FAVORITE_FOOD
import com.enons.paparaproject.core.constants.AppConstant.FOOD_RECIPE
import com.enons.paparaproject.core.constants.AppConstant.KITCHEN_AI
import com.enons.paparaproject.core.constants.AppConstant.SUGGEST_FOOD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor() : ViewModel() {

    private val _imageUrls = MutableStateFlow<List<String>>(emptyList())
    val imageUrls: StateFlow<List<String>> get() = _imageUrls

    init {
        loadImageUrls()
    }

    private fun loadImageUrls() {
        val foodRecipe = FOOD_RECIPE
        val kitchenAi = KITCHEN_AI
        val suggestFood = SUGGEST_FOOD
        val favoriteFood = FAVORITE_FOOD
        _imageUrls.value = listOf(foodRecipe, kitchenAi, suggestFood, favoriteFood)
    }
}
