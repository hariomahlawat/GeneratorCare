package com.hariomahlawat.generatorcare.data

fun getDailyMaintenanceChecklist():List<String>{
    return listOf(
        "General Inspection",
        "Test batteries",
        "Check intake and exhaust",
        "Visual Inspection"
    )
}

fun getWeeklyMaintenanceChecklist():List<String>{
    return listOf(
        "Visual Inspection",
        "Check fluid levels",
        "Check for leaks",
        "Inspect wiring and electrical system",
        "Run the generator (typically no-load, automatic transfer switch exercise cycle)."
    )
}

fun getMonthlyMaintenanceChecklist():List<String>{
    return listOf(
        "Clean generator",
        "Clean surrounding areas",
        "Check coolant levels",
        "Check battery charger / alternator",
        "Check oil levels"
    )
}

fun getSixMonthlyMaintenanceChecklist():List<String>{
    return listOf(
        "Inspect the enclosure, making sure it’s free from debris.",
        "Examine the battery electrolyte level and specific gravity.",
        "Furthermore, check the battery cables and their connections.",
        "Inspect all the drive belts.",
        "Check coolant lines and connections.",
        "Examine for oil leaks and inspect lubrication system hoses and connectors.",
        "Scan the exhaust system, muffler, and exhaust pipe.",
        "Clean all air cleaner units.",
        "Examine air induction piping and connections.",
        "Inspect the DC electrical system, control panel, and all related accessories.",
        "Last, inspect the AC wiring and all related accessories."
    )
}

fun getAsNeededMaintenanceChecklist():List<String>{
    return listOf(
        "Change oil",
        "Change oil filter",
        "Change air filter",
        "Change fuel filter",

    )
}