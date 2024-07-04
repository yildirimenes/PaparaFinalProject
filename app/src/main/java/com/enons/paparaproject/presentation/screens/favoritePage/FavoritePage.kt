package com.enons.paparaproject.presentation.screens.favoritePage
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.enons.paparaproject.R
import com.enons.paparaproject.core.ApiResult.ApiResult
import com.enons.paparaproject.data.local.model.MealEntity
import com.enons.paparaproject.presentation.components.CustomText
import com.enons.paparaproject.presentation.components.DeleteAlertDialog
import com.enons.paparaproject.presentation.components.ErrorComponent
import com.enons.paparaproject.presentation.components.LoadingComponent
import com.enons.paparaproject.presentation.screens.favoritePage.viewmodel.FavoriteViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritePage(navController: NavController) {
    val viewModel: FavoriteViewModel = hiltViewModel()
    val favoriteMeals by viewModel.favoriteMeals.collectAsState()
    var isDeleteDialogVisible by remember { mutableStateOf(false) }
    var mealToDelete: MealEntity? by remember { mutableStateOf(null) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CustomText(
                        stringResource(id = R.string.favorite_page),
                        padding = 4
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        }
    ) {
        when (val result = favoriteMeals) {
            is ApiResult.Success -> {
                LazyColumn {
                    items(result.data) { meal ->
                        var expanded by remember { mutableStateOf(false) }

                        val ingredients = buildString {
                            if (!meal.strIngredient1.isNullOrEmpty()) append("${meal.strIngredient1} - ${meal.strMeasure1}\n")
                            if (!meal.strIngredient2.isNullOrEmpty()) append("${meal.strIngredient2} - ${meal.strMeasure2}\n")
                            if (!meal.strIngredient3.isNullOrEmpty()) append("${meal.strIngredient3} - ${meal.strMeasure3}\n")
                            if (!meal.strIngredient4.isNullOrEmpty()) append("${meal.strIngredient4} - ${meal.strMeasure4}\n")
                            if (!meal.strIngredient5.isNullOrEmpty()) append("${meal.strIngredient5} - ${meal.strMeasure5}\n")
                            if (!meal.strIngredient6.isNullOrEmpty()) append("${meal.strIngredient6} - ${meal.strMeasure6}\n")
                            if (!meal.strIngredient7.isNullOrEmpty()) append("${meal.strIngredient7} - ${meal.strMeasure7}\n")
                            if (!meal.strIngredient8.isNullOrEmpty()) append("${meal.strIngredient8} - ${meal.strMeasure8}\n")
                            if (!meal.strIngredient9.isNullOrEmpty()) append("${meal.strIngredient9} - ${meal.strMeasure9}\n")
                            if (!meal.strIngredient10.isNullOrEmpty()) append("${meal.strIngredient10} - ${meal.strMeasure10}\n")
                            if (!meal.strIngredient11.isNullOrEmpty()) append("${meal.strIngredient11} - ${meal.strMeasure11}\n")
                            if (!meal.strIngredient12.isNullOrEmpty()) append("${meal.strIngredient12} - ${meal.strMeasure12}\n")
                            if (!meal.strIngredient13.isNullOrEmpty()) append("${meal.strIngredient13} - ${meal.strMeasure13}\n")
                            if (!meal.strIngredient14.isNullOrEmpty()) append("${meal.strIngredient14} - ${meal.strMeasure14}\n")
                            if (!meal.strIngredient15.isNullOrEmpty()) append("${meal.strIngredient15} - ${meal.strMeasure15}\n")
                            if (!meal.strIngredient16.isNullOrEmpty()) append("${meal.strIngredient16} - ${meal.strMeasure16}\n")
                            if (!meal.strIngredient17.isNullOrEmpty()) append("${meal.strIngredient17} - ${meal.strMeasure17}\n")
                            if (!meal.strIngredient18.isNullOrEmpty()) append("${meal.strIngredient18} - ${meal.strMeasure18}\n")
                            if (!meal.strIngredient19.isNullOrEmpty()) append("${meal.strIngredient19} - ${meal.strMeasure19}\n")
                            if (!meal.strIngredient20.isNullOrEmpty()) append("${meal.strIngredient20} - ${meal.strMeasure20}\n")
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(it)
                                .padding(8.dp),
                            shape = RoundedCornerShape(4.dp),
                            border = BorderStroke(1.dp, Color.LightGray),
                            colors = CardDefaults.cardColors(
                                Color.White
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(
                                    text = meal.mealName,
                                    color = colorResource(id = R.color.color_two),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.padding(8.dp))
                                Text(
                                    text = "Ingredients",
                                    color = colorResource(id = R.color.color_one),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = ingredients
                                )
                                Spacer(modifier = Modifier.padding(8.dp))
                                AnimatedVisibility(visible = expanded) {
                                    Column {
                                        Text(
                                            text = "Instructions",
                                            color = colorResource(id = R.color.color_one),
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = meal.strInstructions ?: ""
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    DeleteAlertDialog(
                                        isDeleteDialogVisible = isDeleteDialogVisible,
                                        onDismiss = {
                                            isDeleteDialogVisible = false
                                        },
                                        onConfirm = {
                                            mealToDelete?.let {
                                                viewModel.removeFromFavorites(mealName = it.mealName)
                                            }
                                            isDeleteDialogVisible = false
                                        }
                                    )
                                    IconButton(
                                        onClick = {
                                            isDeleteDialogVisible = true
                                            mealToDelete = result.data.find { it.mealName == meal.mealName }
                                        }
                                    ) {
                                        Icon(
                                            Icons.Filled.Delete,
                                            contentDescription = "Delete",
                                            tint = colorResource(id = R.color.color_two),
                                            modifier = Modifier.size(28.dp))
                                    }
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            expanded = !expanded
                                        }) {
                                    Icon(
                                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Toggle Instructions",
                                        tint = Color.Black,
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    )
                                }
                            }
                        }
                    }
                }
            }
            is ApiResult.Error -> {
                ErrorComponent(message = "Try Again") {}
            }
            is ApiResult.Loading -> { LoadingComponent() }
        }
    }
}
