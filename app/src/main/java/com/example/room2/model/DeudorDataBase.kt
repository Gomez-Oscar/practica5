package com.example.room2.model

import androidx.room.Database
import androidx.room.RoomDatabase

//una clase abstracta es una clase que no va a ser instanciada

@Database(entities = arrayOf(Deudor::class), version = 1)
abstract class DeudorDataBase : RoomDatabase() {

    abstract fun DeudorDao(): DeudorDAO
}