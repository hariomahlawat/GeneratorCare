package com.hariomahlawat.generatorcare.data

import com.hariomahlawat.generatorcare.model.CommonFault

fun getCommonFaults():List<CommonFault>{
    return listOf(
        CommonFault(
            "Armature Rewinding",
                listOf(
                    "Due to overload.",
                    "Due to the generator set being exposed to the poor weather conditions like rain, snow etc.",
                    "Due to incorrect distribution in the phases."
                )
        ),
        CommonFault(
                "Engine Oiling Up",
            listOf(
                "Due to overload.",
                "Due to improper lubrication.",
                "Due to engine overheating."
            )
        ),
        CommonFault(
            "Starting trouble",
                listOf(
                    "Clogged fuel filters.",
                    "Improper fuel or fuel-tank dirty.",
                    "Compression poor."
                )
        ),
        CommonFault(
            "Electrical Faults",
                listOf(
                    "Due to overload / incorrect distribution in phases.",
                    "Due to short-circuit",
                    "Due to lose / open wires."
                )
        ),
        CommonFault(
            "Electronic Sensor/EVR faulty",
                listOf(
                    "Due to overload / incorrect distribution in phases.",
                    "Due to short-circuit in output supply.",
                    "Due to the generator set being exposed to the poor weather conditions like rain, snow etc."
                )
        )
    )
}