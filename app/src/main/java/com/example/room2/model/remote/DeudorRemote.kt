package com.example.room2.model.remote

//firebase no necesita etiquetas
//se necesita un constuctor vacio => se puede inicializar las viariables vacias

class DeudorRemote (
    val id: String? = "",
    val nombre: String= "",
    val telefono: String= "",
    val cantidad: Long= 0,
    val urlPhoto: String = ""
)