package com.hariomahlawat.generatorcare.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hariomahlawat.generatorcare.screens.SplashScreen
import com.hariomahlawat.generatorcare.screens.add_generator.AddGeneratorScreen
import com.hariomahlawat.generatorcare.screens.advisory.GeneratorAdvisoryScreen
import com.hariomahlawat.generatorcare.screens.alerts.MaintenanceAlertScreen
import com.hariomahlawat.generatorcare.screens.checklist.MaintenanceChecklistScreen
import com.hariomahlawat.generatorcare.screens.home.HomeScreen
import com.hariomahlawat.generatorcare.screens.logbook.LogbookScreen
import com.hariomahlawat.generatorcare.screens.maintenance_record.MaintenanceRecordScreen
import com.hariomahlawat.generatorcare.screens.my_generators.GeneratorsListScreen

@Composable
fun GeneratorCareNavigation() {
    val navController = rememberNavController()
    val generatorId =""

    NavHost(navController = navController, startDestination = GeneratorCareScreens.SplashScreen.name){

        composable(GeneratorCareScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(GeneratorCareScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(GeneratorCareScreens.AddGeneratorScreen.name) {
            AddGeneratorScreen(navController = navController)
        }

        composable(GeneratorCareScreens.GeneratorAdvisoryScreen.name) {
            GeneratorAdvisoryScreen(navController = navController)
        }

        composable(GeneratorCareScreens.MaintenanceAlertsScreen.name) {
            MaintenanceAlertScreen(navController = navController)
        }

        composable(GeneratorCareScreens.MaintenanceRecordScreen.name) {
            MaintenanceRecordScreen(navController = navController)
        }

        composable(GeneratorCareScreens.GeneratorsListScreen.name) {
            GeneratorsListScreen(navController = navController)
        }
        composable(GeneratorCareScreens.MaintenanceChecklistScreen.name) {
            MaintenanceChecklistScreen(navController = navController)
        }

        composable(GeneratorCareScreens.LogbookScreen.name+"/{generatorId}",
            arguments = listOf(
                navArgument("generatorId"){
                    type = NavType.StringType
                    defaultValue=""
                }
            )
        ) {
            it ->
            val id = requireNotNull(it.arguments).getString("generatorId")
            LogbookScreen(navController = navController, generatorId = id)

        }


    }

}
