package com.enons.paparaproject.presentation.screens.recipePage
import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.enons.paparaproject.R
import com.enons.paparaproject.presentation.components.CustomText
import com.enons.paparaproject.presentation.components.ErrorComponent
import com.enons.paparaproject.presentation.components.LoadingComponent
import com.enons.paparaproject.presentation.components.RecipeSuccessComponent
import com.enons.paparaproject.presentation.screens.recipePage.viewmodel.RecipeViewIntent
import com.enons.paparaproject.presentation.screens.recipePage.viewmodel.RecipeViewModel
import com.enons.paparaproject.presentation.screens.recipePage.viewmodel.RecipeViewState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipePage(navController: NavController) {
    val viewModel: RecipeViewModel = hiltViewModel()
    val state by viewModel.state

    LaunchedEffect(Unit) {
        viewModel.processIntent(RecipeViewIntent.LoadFirstRecipe)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CustomText(stringResource(id = R.string.recipe_page),
                        padding = 4
                    )
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
            is RecipeViewState.Loading -> LoadingComponent()
            is RecipeViewState.Success -> {
                val recipes = (state as RecipeViewState.Success).recipes
                RecipeSuccessComponent(recipes = recipes, onSearchClicked = { query ->
                    viewModel.processIntent(RecipeViewIntent.SearchRecipes(query))
                })
            }
            is RecipeViewState.Error -> {
                val message = (state as RecipeViewState.Error).message
                ErrorComponent(message = message, onRefreshClicked = {
                    viewModel.processIntent(RecipeViewIntent.LoadFirstRecipe)
                })
            }
        }
    }
}