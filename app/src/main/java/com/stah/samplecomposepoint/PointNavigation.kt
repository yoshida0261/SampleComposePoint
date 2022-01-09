package com.stah.samplecomposepoint

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.stah.samplecomposepoint.AppDestinations.DAY
import com.stah.samplecomposepoint.AppDestinations.YEAR
import androidx.navigation.compose.rememberNavController as rememberNavController1

object AppDestinations {
    const val POINT_MONTHLY = "point_monthly"
    const val POINT_HISTORY = "point_history"
    const val YEAR = "year"
    const val DAY = "day"
}

@ExperimentalMaterialApi
@Composable
fun PointNavigation(
    startDestination: String = AppDestinations.POINT_MONTHLY
) {
    val navController = rememberNavController1()
    val actions = remember(navController) { PointHistoryActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            AppDestinations.POINT_MONTHLY
        ) { backStackEntry ->

        }
        composable(
            "${AppDestinations.POINT_HISTORY}/{$YEAR}/{$DAY}",
            arguments = listOf(
                navArgument(YEAR) {
                    type = NavType.IntType
                },
                navArgument(DAY) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

        }
    }
}


private class PointHistoryActions(
    navController: NavController
) {
    val selectedHistory: (Int, Int) -> Unit = { year, day: Int ->
        navController.navigate("${AppDestinations.POINT_HISTORY}/$year/$day")
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}
