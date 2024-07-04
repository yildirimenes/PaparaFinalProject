package com.enons.paparaproject.presentation.screens.homePage
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.enons.paparaproject.R
import com.enons.paparaproject.navigation.Screen
import com.enons.paparaproject.presentation.components.ClickableImageCard
import com.enons.paparaproject.presentation.components.CustomText
import com.enons.paparaproject.presentation.components.ImageSliders
import com.enons.paparaproject.presentation.screens.homePage.viewmodel.HomePageViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
    val viewModel: HomePageViewModel = viewModel()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, end = 4.dp)
                    ) {
                        CustomText(
                            text = stringResource(id = R.string.app_name),
                            padding = 8
                        )
                        IconButton(
                            onClick = {
                                navController.navigate(Screen.LatestScreen.route)
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_fiber_new_24),
                                tint = Color.Gray,
                                modifier = Modifier.size(36.dp),
                                contentDescription = ""
                            )
                        }
                    }
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .padding(it)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            ImageSliders(modifier = Modifier.weight(15f))
            Spacer(modifier = Modifier.weight(3f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(18f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    ClickableImageCard(
                        imageUrl = viewModel.imageUrls.value[0],
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(7f),
                        text = stringResource(id = R.string.recipe_page),
                        onClick = { navController.navigate(Screen.RecipePage.route) },
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    ClickableImageCard(
                        imageUrl = viewModel.imageUrls.value[1],
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(7f),
                        text = stringResource(id = R.string.chat_ai_page),
                        onClick = { navController.navigate(Screen.ChatAiScreen.route) },
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    ClickableImageCard(
                        imageUrl = viewModel.imageUrls.value[2],
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(7f),
                        text = stringResource(id = R.string.suggest_page),
                        onClick = { navController.navigate(Screen.RandomRecipePage.route) },

                        )
                    Spacer(modifier = Modifier.weight(1f))
                    ClickableImageCard(
                        imageUrl = viewModel.imageUrls.value[3],
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(7f),
                        text = stringResource(id = R.string.favorite_page),
                        onClick = { navController.navigate(Screen.FavoriteScreen.route) },
                    )
                }

            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }

}
