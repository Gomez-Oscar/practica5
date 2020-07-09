package com.example.room2
//esta clase se debe llamar igual al nombre del paquete
import android.app.Application
import androidx.room.Room
import com.example.room2.model.DeudorDataBase
import com.example.room2.usuario.UsuarioDataBase

//para hacer que esto se ejecute se debe modificar el manifest

class ROOM2 : Application() {

    companion object{
        lateinit var database : DeudorDataBase
        lateinit var database2 : UsuarioDataBase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            DeudorDataBase::class.java,
            "misdeudores_db"
        ).allowMainThreadQueries().build()

        database2 = Room.databaseBuilder(
            this,
            UsuarioDataBase::class.java,
            "mis_usuarios_db"
        ).allowMainThreadQueries().build()
    }

    //el patron builder es un fragmento de codigo que permite construir algo,
    // en este caso nuestra base de datos
}