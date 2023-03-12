package com.hariomahlawat.generatorcare.navigation

enum class GeneratorCareScreens {
    SplashScreen,
    HomeScreen,
    AddGeneratorScreen,
    GeneratorAdvisoryScreen,
    MaintenanceAlertsScreen,
    GeneratorsListScreen,
    MaintenanceRecordScreen,
    LogbookScreen,
    MaintenanceChecklistScreen;

    companion object {
        fun fromRoute(route: String?): GeneratorCareScreens
        = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            HomeScreen.name -> HomeScreen
            AddGeneratorScreen.name -> AddGeneratorScreen
            GeneratorAdvisoryScreen.name -> GeneratorAdvisoryScreen
            MaintenanceAlertsScreen.name -> MaintenanceAlertsScreen
            GeneratorsListScreen.name -> GeneratorsListScreen
            MaintenanceRecordScreen.name -> MaintenanceRecordScreen
            LogbookScreen.name -> LogbookScreen
            MaintenanceChecklistScreen.name -> MaintenanceChecklistScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}