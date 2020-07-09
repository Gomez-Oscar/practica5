package com.example.room2.model

import androidx.room.*

@Dao
interface DeudorDAO {
    @Insert
    fun crearDeudor(deudor: Deudor)//se le pasa la entidad Deudor

    @Query ("SELECT * FROM tabla_deudor WHERE nombre Like :nombre")
    fun buscarDeudor(nombre:String): Deudor

    @Update
    fun actualizarDeudor(deudor : Deudor)

    @Delete
    fun borrarDeudor(deudor : Deudor)

    @Query("SELECT * FROM tabla_deudor" )
    fun getDeudores(): List<Deudor>

}