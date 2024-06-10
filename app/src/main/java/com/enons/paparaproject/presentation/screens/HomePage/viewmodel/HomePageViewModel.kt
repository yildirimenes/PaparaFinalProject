package com.enons.paparaproject.presentation.screens.HomePage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enons.paparaproject.utils.AppConstant.FAVORITE_FOOD
import com.enons.paparaproject.utils.AppConstant.FOOD_RECIPE
import com.enons.paparaproject.utils.AppConstant.KITCHEN_AI
import com.enons.paparaproject.utils.AppConstant.SUGGEST_FOOD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor() : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: MutableStateFlow<Boolean> = _isLoading

    fun loading() {
        viewModelScope.launch {
            delay(2_000)
            _isLoading.value = false
        }
    }

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
