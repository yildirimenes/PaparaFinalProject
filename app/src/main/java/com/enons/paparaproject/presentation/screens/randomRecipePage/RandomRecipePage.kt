package com.enons.paparaproject.presentation.screens.randomRecipePage

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.enons.paparaproject.R
import com.enons.paparaproject.presentation.components.ErrorComponent
import com.enons.paparaproject.presentation.components.LoadingComponent
import com.enons.paparaproject.presentation.components.RandomSuccessComponent
import com.enons.paparaproject.presentation.screens.randomRecipePage.viewmodel.RandomRecipeViewModel
import com.enons.paparaproject.presentation.screens.randomRecipePage.viewmodel.RandomRecipeViewState
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomRecipePage(navController: NavController) {
    val viewModel: RandomRecipeViewModel = hiltViewModel()
    val state by viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.suggest_page))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
    ) {
        when (state) {
            is RandomRecipeViewState.Loading -> LoadingComponent()
            is RandomRecipeViewState.Success -> {
                val recipe = (state as RandomRecipeViewState.Success).recipe
                RandomSuccessComponent(recipe = recipe)
            }
            is RandomRecipeViewState.Error -> {
                val message = (state as RandomRecipeViewState.Error).message
                ErrorComponent(message = message, onRefreshClicked = {
                    viewModel.loadRandomRecipe()
                })
            }
        }
    }
}