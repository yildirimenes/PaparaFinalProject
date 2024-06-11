package com.enons.paparaproject
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.enons.paparaproject.navigation.Navigation
import com.enons.paparaproject.presentation.screens.HomePage.viewmodel.HomePageViewModel
import com.enons.paparaproject.presentation.ui.theme.PaparaFinalProjectTheme
import com.enons.paparaproject.service.NotificationWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit


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
        scheduleNotificationWork()
    }

    private fun scheduleNotificationWork() {
        val notificationWorkRequest = PeriodicWorkRequestBuilder<NotificationWorker>(24, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(this).enqueue(notificationWorkRequest)
    }
}

private external fun getApiKeyFromNdk(): String

fun getApiKey() : String {
    return getApiKeyFromNdk()
}



