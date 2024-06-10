package com.enons.paparaproject
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.enons.paparaproject.navigation.Navigation
import com.enons.paparaproject.presentation.screens.HomePage.viewmodel.HomePageViewModel
import com.enons.paparaproject.presentation.ui.theme.PaparaFinalProjectTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        init {
            System.loadLibrary("paparaproject")
        }
    }
    private val homePageViewModel by viewModels<HomePageViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homePageViewModel.loading()
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                homePageViewModel.isLoading.value
            }
        }
        setContent {
            PaparaFinalProjectTheme {
                val navController = rememberNavController()
                Navigation(navController = navController)
            }
        }
    }
}

private external fun getApiKeyFromNdk(): String

fun getApiKey() : String {
    return getApiKeyFromNdk()
}
