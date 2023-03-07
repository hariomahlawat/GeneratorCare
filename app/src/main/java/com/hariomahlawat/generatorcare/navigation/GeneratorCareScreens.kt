package com.hariomahlawat.generatorcare.navigation

enum class GeneratorCareScreens {
    SplashScreen,
    HomeScreen,
    AddGeneratorScreen,
    AdvisoryScreen,
    MaintenanceAlertsScreen,
    GeneratorsListScreen,
    MaintenanceRecordScreen,
    LogbookScreen;

    companion object {
        fun fromRoute(route: String?): GeneratorCareScreens
        = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            HomeScreen.name -> HomeScreen
            AddGeneratorScreen.name -> AddGeneratorScreen
            AdvisoryScreen.name -> AdvisoryScreen
            MaintenanceAlertsScreen.name -> MaintenanceAlertsScreen
            GeneratorsListScreen.name -> GeneratorsListScreen
            MaintenanceRecordScreen.name -> MaintenanceRecordScreen
            LogbookScreen.name -> LogbookScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}