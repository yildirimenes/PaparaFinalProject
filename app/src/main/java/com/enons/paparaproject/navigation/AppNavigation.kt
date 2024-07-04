package com.enons.paparaproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.enons.paparaproject.presentation.screens.chatAiPage.ChatAiPage
import com.enons.paparaproject.presentation.screens.favoritePage.FavoritePage
import com.enons.paparaproject.presentation.screens.homePage.HomePage
import com.enons.paparaproject.presentation.screens.latestRecipePage.LatestRecipePage
import com.enons.paparaproject.presentation.screens.randomRecipePage.RandomRecipePage
import com.enons.paparaproject.presentation.screens.recipePage.RecipePage

sealed class Screen(val route: String) {
    object HomePage : Screen("homepage_screen")
    object RecipePage : Screen("recipe_screen")
    object RandomRecipePage : Screen("random_recipe_screen")
    object ChatAiScreen : Screen("chat_ai_screen")
    object FavoriteScreen : Screen("favorite_screen")
    object LatestScreen : Screen("latest_screen")
    object LatestRecipePage : Screen("latestRecipePage")
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
        composable(Screen.LatestScreen.route) {
            LatestRecipePage()
        }
        composable(Screen.LatestRecipePage.route) {
            LatestRecipePage()
        }
    }

}