package com.example.room2.usuario

import androidx.room.*
import com.example.room2.model.Deudor

@Dao
interface UsuarioDAO {
    @Insert
    fun crearUsuario(usuario: Usuario)//se le pasa la entidad Usuario

    @Query ("SELECT * FROM tabla_usuario WHERE correo Like :correo")
    fun buscarUsuario(correo:String): Usuario

    @Delete
    fun borrarUsuario(usuario : Usuario)
}