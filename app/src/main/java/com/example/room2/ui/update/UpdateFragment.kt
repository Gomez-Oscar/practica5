package com.example.room2.ui.update

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.room2.R
import com.example.room2.ROOM2
import com.example.room2.model.local.Deudor
import com.example.room2.model.local.DeudorDAO
import com.example.room2.model.remote.DeudorRemote
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.bt_buscar
import kotlinx.android.synthetic.main.fragment_update.et_nombre

class UpdateFragment : Fragment() {

    var idDeudorFireBase : String? = ""

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideDeudorDatosET()

        var idDeudor = 0

        val deudorDAO: DeudorDAO = ROOM2.database.DeudorDao()

        //estas variables se ponen aqui para evitar repetir codigo
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("deudores")

        bt_buscar.setOnClickListener {
            val nombre = et_nombre.text.toString()

            //buscarEnRoom(deudorDAO, nombre, idDeudor)

            buscarEnFireBase(nombre,myRef)

        }

        bt_actualizar.setOnClickListener{

            val nombre = et_nombre.text.toString()
            val telefono = et_telefono.text.toString()
            val cantidad = et_cantidad.text.toString()


            if (nombre.isEmpty() || nombre.isBlank()) {
                Toast.makeText(context,"Ingrese un Nombre",Toast.LENGTH_SHORT).show()

            }else if(telefono.isEmpty() || telefono.isBlank()){
                Toast.makeText(context,"Ingrese un Teléfono",Toast.LENGTH_SHORT).show()

            }else if(cantidad.isEmpty() || cantidad.isBlank()){
                Toast.makeText(context,"Ingrese una Cantidad",Toast.LENGTH_SHORT).show()

            }else {

                //actualizarEnRoom(idDeudor, nombre, telefono, cantidad, deudorDAO)

                actualizarEnFireBase(myRef)

                habilitarWidgetsBuscar()
            }

        }

    }

    private fun actualizarEnFireBase(
        myRef: DatabaseReference
    ) {

        val childUpdate = HashMap<String, Any>()
        childUpdate["nombre"] = et_nombre.text.toString()
        childUpdate["telefono"] = et_telefono.text.toString()
        childUpdate["cantidad"] = et_cantidad.text.toString().toLong()

        myRef.child(idDeudorFireBase!!).updateChildren(childUpdate)
    }

    private fun actualizarEnRoom(
        idDeudor: Int,
        nombre: String,
        telefono: String,
        cantidad: String,
        deudorDAO: DeudorDAO
    ) {
        val deudor = Deudor(
            idDeudor,
            nombre,
            telefono,
            cantidad.toLong()
        )
        deudorDAO.actualizarDeudor(deudor)
    }

    private fun habilitarWidgetsBuscar() {
        et_telefono.visibility = View.GONE
        et_cantidad.visibility = View.GONE
        bt_buscar.visibility = View.VISIBLE
        bt_actualizar.visibility = View.GONE
    }

    private fun buscarEnFireBase(
        nombre: String,
        myRef: DatabaseReference
    ) {

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("deudores")
        var deudorExiste = false

        val postListener = object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("Buscar","Entro")
                for(datasnapshot: DataSnapshot in snapshot.children){
                    val deudor = datasnapshot.getValue(DeudorRemote::class.java)
                    if (deudor?.nombre == nombre){
                        deudorExiste = true
                        habilitarWidgetsActualizar()
                        et_telefono.setText(deudor.telefono)
                        et_cantidad.setText(deudor.cantidad.toString())
                        idDeudorFireBase = deudor.id
                    }
                }
                if (!deudorExiste)
                    Toast.makeText(context,"Deudor no existe",Toast.LENGTH_SHORT).show()

            }
        }

        myRef.addListenerForSingleValueEvent(postListener)
    }

    private fun buscarEnRoom(
        deudorDAO: DeudorDAO,
        nombre: String,
        idDeudor: Int
    ) {
        var idDeudor1 = idDeudor
        val deudor = deudorDAO.buscarDeudor(nombre)

        if (deudor != null) {

            idDeudor1 = deudor.id

            habilitarWidgetsActualizar()

            et_telefono.setText(deudor.telefono)
            et_cantidad.setText(deudor.cantidad.toString())
        } else {
            Toast.makeText(context, "Deudor No Existe", Toast.LENGTH_SHORT).show()
        }
    }

    private fun habilitarWidgetsActualizar() {
        et_telefono.visibility = View.VISIBLE
        et_cantidad.visibility = View.VISIBLE
        bt_buscar.visibility = View.GONE
        bt_actualizar.visibility = View.VISIBLE
    }

    private fun hideDeudorDatosET() {
        et_telefono.visibility = View.GONE //para ocultarlos
        et_cantidad.visibility = View.GONE
        bt_actualizar.visibility = View.GONE
    }
}