package com.example.bt2t4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bt2t4.data.Task
import com.example.bt2t4.ui.screens.DetailScreen
import com.example.bt2t4.ui.screens.ListScreen
import com.example.bt2t4.ui.screens.OnboardingScreen
import com.example.bt2t4.ui.screens.SecondScreen
import com.example.bt2t4.ui.screens.ThirdScreen
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { OnboardingScreen(navController) }
                composable("SecondScreen") { SecondScreen(navController) }
                composable("ThirdScreen") { ThirdScreen(navController) }
                composable("ListScreen") { ListScreen(navController) }
                composable(
                    "detailScreen/{taskJson}",
                    arguments = listOf(navArgument("taskJson") { type = NavType.StringType })
                ) { backStackEntry ->
                    val taskJson = backStackEntry.arguments?.getString("taskJson")
                    val task = Gson().fromJson(taskJson, Task::class.java)
                    DetailScreen(navController, task)
                }
            }
        }
    }
}
