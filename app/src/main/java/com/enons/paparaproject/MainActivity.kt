package com.enons.paparaproject
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.enons.paparaproject.navigation.Navigation
import com.enons.paparaproject.presentation.screens.homePage.viewmodel.HomePageViewModel
import com.enons.paparaproject.presentation.ui.theme.PaparaFinalProjectTheme
import com.enons.paparaproject.workers.RecipeWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Install the splash screen
        installSplashScreen()

        setContent {
            PaparaFinalProjectTheme {
                val navController = rememberNavController()
                Navigation(navController = navController)
            }
        }
        scheduleDailyRecipeNotification(applicationContext)
    }
    private fun scheduleDailyRecipeNotification(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<RecipeWorker>(24, TimeUnit.HOURS)
            .build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }

}


