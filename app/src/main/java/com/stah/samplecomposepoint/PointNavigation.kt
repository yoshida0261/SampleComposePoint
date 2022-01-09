package com.stah.samplecomposepoint

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.stah.samplecomposepoint.AppDestinations.DAY
import com.stah.samplecomposepoint.AppDestinations.YEAR
import com.stah.samplecomposepoint.point.PointHistory
import com.stah.samplecomposepoint.point.PointMonthlyScreen
import androidx.navigation.compose.rememberNavController as rememberNavController1

object AppDestinations {
    const val POINT_MONTHLY = "point_monthly"
    const val POINT_HISTORY = "point_history"
    const val YEAR = "year"
    const val DAY = "day"
}

@ExperimentalPagerApi
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
            PointMonthlyScreen(actions.selectedHistory, actions.navigateUp)
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

            val p = PointHistory(
                point = 1000,
                pointName = "インタビューポイント",
                itemName = "",
                date = "2021年xx月xx日 xx:xx"
            )
            val list = listOf(p,p,p,p,p)
            PointHistory("${backStackEntry.arguments?.get(YEAR)}年${backStackEntry.arguments?.get(DAY)}月の履歴",list, actions.navigateUp)
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
