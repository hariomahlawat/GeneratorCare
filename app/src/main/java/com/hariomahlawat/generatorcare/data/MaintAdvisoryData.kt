package com.hariomahlawat.generatorcare.data

fun getMaintAdvisory():List<List<String>>{
    val starting_procedure:List<String> = listOf(
        "Check oil and fuel level, top up if necessary.",
        "Check water level in batteries and top up with distilled water (if required).",
        "Check fan belt tension and adjust if necessary.",
        "Check tightness of nuts, screws and all flexible pipes connections.",
        "Check all fuses, output sockets and electrical connections.",
        "Before starting the generator, ensure main supply is in 'OFF position'",
        "Do not engage self starter switch for more than 10 seconds.",
        "Allow the engine to warm up for five minutes.",
        "Allow load on the generator gradually and check for correct phase distribution."
    )

    val stopping_procedure:List<String> = listOf(
        "Remove the load from the generator",
        "Allow the engine to run in no load condition for few minutes.",
        "Shut off the engine by electrical / manual lever.",
        "Do not use de-compression lever for stopping the engine."
    )

    val dos:List<String> = listOf(
        "Ensure that generator stands on hard and levelled surface.",
        "Ground the earthing rod.",
        "Ensure that overloading is avoided.",
        "If the generator-set is run in a confined space, make sure that there is enough ventilation and the engine exhaust is taken out.",
        "Do not run generator-set unprotected in dusty condition, storm or rain. Id dust of water enters the generator-set, clean it immediately.",
        "Remove the alternator fan belt when generator is run without battery.",
        "Stop the generator immediately if any vibration in sound of generator is heard.",
        "Stop the generator immediately if fluctuation in voltage is observed.",
        "Stop the generator immediately on hearing of any knocking or metallic abrasive sound."
    )

    return listOf(starting_procedure, stopping_procedure, dos)
}

fun getStartingProcedureFromMaintAdvisory():List<String>{
    return getMaintAdvisory()[0]
}
fun getStoppingProcedureFromMaintAdvisory():List<String>{
    return getMaintAdvisory()[1]
}
fun getDosFromMaintAdvisory():List<String>{
    return getMaintAdvisory()[2]
}