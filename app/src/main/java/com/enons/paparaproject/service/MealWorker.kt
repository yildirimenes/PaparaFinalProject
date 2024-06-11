package com.enons.paparaproject.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MealWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val links = listOf(
                    "https://www.themealdb.com/api/json/v1/1/lookup.php?i=53085",
                    "https://www.themealdb.com/api/json/v1/1/lookup.php?i=53083",
                    "https://www.themealdb.com/api/json/v1/1/lookup.php?i=53082",
                    "https://www.themealdb.com/api/json/v1/1/lookup.php?i=53081",
                    "https://www.themealdb.com/api/json/v1/1/lookup.php?i=53080",
                    "https://www.themealdb.com/api/json/v1/1/lookup.php?i=53079",
                    "https://www.themealdb.com/api/json/v1/1/lookup.php?i=53077"
                )

                for (link in links) {
                    val url = URL(link)
                    val connection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "GET"

                    val response = connection.inputStream.bufferedReader().readText()
                    val json = JSONObject(response)
                    val meal = json.getJSONArray("meals").getJSONObject(0)
                    val mealName = meal.getString("strMeal")

                    val notificationHelper = NotificationHelper(applicationContext)
                    notificationHelper.sendNotification("New Meal", mealName)
                }

                Result.success()
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
}
