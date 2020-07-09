package com.example.room2.model

import androidx.room.ColumnInfo // con columninfo se le da el nombre a las columnas
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_deudor")//se indica que esta es la entidad y pertenece a un tabla

class Deudor(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,//se genera la llave primaria (empieza desde 1)
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "telefono") val telefono: String,
    @ColumnInfo(name = "cantidad") val cantidad: Long
)