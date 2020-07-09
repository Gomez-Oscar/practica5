package com.example.room2.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.room2.R
import com.example.room2.model.remote.DeudorRemote
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_create.*

class CreateFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_create, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mostrarMensajeBienvenida()



        bt_guardar.setOnClickListener{
            val nombre = et_nombre.text.toString()
            val telefono = et_telefono.text.toString()
            val cantidad = et_cantidad.text.toString()

            if(nombre.isEmpty() || nombre.isBlank()) {
                Toast.makeText(context,"Ingrese un Nombre",Toast.LENGTH_SHORT).show()

            }else if(telefono.isEmpty() || telefono.isBlank()){
                Toast.makeText(context,"Ingrese un Teléfono",Toast.LENGTH_SHORT).show()

            }else if(cantidad.isEmpty() || cantidad.isBlank()){
                Toast.makeText(context,"Ingrese un Cantidad",Toast.LENGTH_SHORT).show()

            }else{

                //val deudor = Deudor(NULL, nombre, telefono, cantidad.toLong())
                //val deudorDAO: DeudorDAO = ROOM2.database.DeudorDao()
                //deudorDAO.crearDeudor(deudor)

                guardarEnFireBase(nombre,telefono,cantidad.toLong())

                cleanEditText()
            }

        }
    }

    private fun mostrarMensajeBienvenida() {
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = mAuth.currentUser
        //se accede a los datos almacenado en firebase
        val correo = user?.email
        Toast.makeText(context, "$correo", Toast.LENGTH_SHORT).show()
    }

    private fun guardarEnFireBase(nombre: String, telefono: String, cantidad: Long) {
        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef : DatabaseReference = database.getReference("deudores")
        val id = myRef.push().key

        val deudor = DeudorRemote(
            id,
            nombre,
            telefono,
            cantidad
        )
        myRef.child(id!!).setValue(deudor)
    }

    private fun cleanEditText() {
        et_nombre.setText("")//se limpian los campos de entrada
        et_telefono.setText("")
        et_cantidad.setText("")
    }
}
