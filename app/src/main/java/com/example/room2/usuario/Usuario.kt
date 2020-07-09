package com.example.room2.usuario

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_usuario")

class Usuario(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,//se genera la llave primaria (empieza desde 1)
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "correo") val correo: String,
    @ColumnInfo(name = "contraseña") val contrasena: String
)