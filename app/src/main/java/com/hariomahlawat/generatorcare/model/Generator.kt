package com.hariomahlawat.generatorcare.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

@Entity(tableName = "generators_tbl" )
data class Generator @RequiresApi(Build.VERSION_CODES.O) constructor(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "registration_number" )
    val registration_number: String,

    @ColumnInfo(name = "make")
    val make: String,

    @ColumnInfo(name = "model")
    val model: String,

)