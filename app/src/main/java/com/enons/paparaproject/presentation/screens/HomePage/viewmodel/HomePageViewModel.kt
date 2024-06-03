package com.enons.paparaproject.presentation.screens.HomePage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enons.paparaproject.utils.AppConstant.FAVORITE_FOOD
import com.enons.paparaproject.utils.AppConstant.FOOD_RECIPE
import com.enons.paparaproject.utils.AppConstant.KITCHEN_AI
import com.enons.paparaproject.utils.AppConstant.SUGGEST_FOOD

class HomePageViewModel : ViewModel() {
    private val _imageUrls = MutableLiveData<List<String>>()
    val imageUrls: LiveData<List<String>> = _imageUrls

    init {
        loadImageUrls()
    }

    private fun loadImageUrls() {
        val foodRecipe = FOOD_RECIPE
        val kitchenAi = KITCHEN_AI
        val suggestFood = SUGGEST_FOOD
        val favoriteFood = FAVORITE_FOOD
        _imageUrls.value = listOf(foodRecipe,kitchenAi,suggestFood,favoriteFood)
    }
}
