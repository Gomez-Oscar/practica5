package com.example.room2.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room2.model.local.Deudor
import com.example.room2.model.local.DeudorDAO

//una clase abstracta es una clase que no va a ser instanciada

@Database(entities = arrayOf(Deudor::class), version = 1)
abstract class DeudorDataBase : RoomDatabase() {

    abstract fun DeudorDao(): DeudorDAO
}