package com.enons.paparaproject.presentation.screens.latestRecipePage

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.enons.paparaproject.R
import com.enons.paparaproject.presentation.components.ErrorComponent
import com.enons.paparaproject.presentation.components.LoadingComponent
import com.enons.paparaproject.presentation.components.RandomSuccessComponent
import com.enons.paparaproject.presentation.screens.latestRecipePage.viewmodel.LatestRecipeViewModel
import com.enons.paparaproject.presentation.screens.latestRecipePage.viewmodel.LatestRecipeViewState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LatestRecipePage() {
    val viewModel: LatestRecipeViewModel = hiltViewModel()
    val state by viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.latest_page))
                },
            )
        },
    ) {
        when (state) {
            is LatestRecipeViewState.Loading -> LoadingComponent()
            is LatestRecipeViewState.Success -> {
                val recipe = (state as LatestRecipeViewState.Success).recipe
                RandomSuccessComponent(recipe = recipe)
            }
            is LatestRecipeViewState.Error -> {
                val message = (state as LatestRecipeViewState.Error).message
                ErrorComponent(message = message, onRefreshClicked = {
                    viewModel.loadLatestRecipe()
                })
            }
        }
    }
}