package com.example.room2.ui.read

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.room2.R
import com.example.room2.ROOM2
import com.example.room2.model.local.DeudorDAO
import com.example.room2.model.remote.DeudorRemote
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_read.*

class ReadFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_read, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_buscar.setOnClickListener {
            val nombre = et_nombre.text.toString()

            //buscarEnRoom(nombre)
            buscarEnFireBase(nombre)
        }
    }

    private fun buscarEnFireBase(nombre: String) {

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("deudores")
        var deudorExiste = false

        val postListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                //con esto se visualuza en consola la info de la base de datos
                //Log.d("data",snapshot.toString())
                for (datasnapshot: DataSnapshot in snapshot.children) {
                    val deudor = datasnapshot.getValue(DeudorRemote::class.java)
                    if (deudor?.nombre == nombre) {
                        deudorExiste = true
                        mostrarDeudor(deudor.nombre, deudor.telefono, deudor.cantidad)
                    }
                }
                if (!deudorExiste)
                    Toast.makeText(context, "Deudor no existe", Toast.LENGTH_SHORT).show()
            }
        }

        myRef.addValueEventListener(postListener)

    }

    private fun buscarEnRoom(nombre: String) {
        val deudorDAO: DeudorDAO = ROOM2.database.DeudorDao()
        val deudor = deudorDAO.buscarDeudor(nombre)

        if (deudor != null) {
            mostrarDeudor(deudor.nombre, deudor.telefono, deudor.cantidad)

        } else {
            Toast.makeText(context, "Deudor no existe", Toast.LENGTH_SHORT).show()
        }
    }

    private fun mostrarDeudor(
        nombre: String,
        telefono: String,
        cantidad: Long
    ) {
        tv_resultado.text =
            "Nombre: $nombre\nTelefono: $telefono\nCantidad: $cantidad"
    }
}











































