package com.enons.paparaproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.enons.paparaproject.presentation.screens.ChatAIPage.ChatAiPage
import com.enons.paparaproject.presentation.screens.FavoritePage.FavoritePage
import com.enons.paparaproject.presentation.screens.HomePage.HomePage
import com.enons.paparaproject.presentation.screens.LatestRecipePage.LatestRecipePage
import com.enons.paparaproject.presentation.screens.RandomRecipePage.RandomRecipePage
import com.enons.paparaproject.presentation.screens.RecipePage.RecipePage

sealed class Screen(val route: String) {
    object HomePage : Screen("homepage_screen")
    object RecipePage : Screen("recipe_screen")
    object RandomRecipePage : Screen("random_recipe_screen")
    object ChatAiScreen : Screen("chat_ai_screen")
    object FavoriteScreen : Screen("favorite_screen")
    object LatestRecipePage : Screen("latest_recipe_screen")

}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomePage.route) {
        composable(Screen.HomePage.route) {
            HomePage(navController)
        }
        composable(Screen.RecipePage.route) {
            RecipePage(navController)
        }
        composable(Screen.RandomRecipePage.route) {
            RandomRecipePage(navController)
        }
        composable(Screen.ChatAiScreen.route) {
            ChatAiPage(navController = navController)
        }
        composable(Screen.FavoriteScreen.route) {
            FavoritePage(navController)
        }
    }

}