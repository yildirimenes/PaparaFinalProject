package com.enons.paparaproject
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.enons.paparaproject.navigation.Navigation
import com.enons.paparaproject.presentation.ui.theme.PaparaFinalProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaparaFinalProjectTheme {
                val navController = rememberNavController()
                Navigation(navController = navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PaparaFinalProjectTheme {
        Navigation(rememberNavController())
    }
}