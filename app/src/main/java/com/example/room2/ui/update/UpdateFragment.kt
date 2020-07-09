package com.example.room2.ui.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.room2.R
import com.example.room2.ROOM2
import com.example.room2.model.local.Deudor
import com.example.room2.model.local.DeudorDAO
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.bt_buscar
import kotlinx.android.synthetic.main.fragment_update.et_nombre

class UpdateFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_update, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_telefono.visibility = View.GONE //para ocultarlos
        et_cantidad.visibility = View.GONE
        bt_actualizar.visibility = View.GONE

        var idDeudor = 0

        val deudorDAO: DeudorDAO = ROOM2.database.DeudorDao()

        bt_buscar.setOnClickListener {
            val nombre = et_nombre.text.toString()

            val deudor = deudorDAO.buscarDeudor(nombre)

            if (deudor != null) {

                idDeudor = deudor.id

                et_telefono.visibility = View.VISIBLE
                et_cantidad.visibility = View.VISIBLE

                et_telefono.setText(deudor.telefono)
                et_cantidad.setText(deudor.cantidad.toString())

                bt_buscar.visibility = View.GONE
                bt_actualizar.visibility = View.VISIBLE

            }else {
                Toast.makeText(context,"Deudor No Existe",Toast.LENGTH_SHORT).show()

            }

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
                val deudor = Deudor(
                    idDeudor,
                    nombre,
                    telefono,
                    cantidad.toLong()
                )

                deudorDAO.actualizarDeudor(deudor)

                et_telefono.visibility = View.GONE
                et_cantidad.visibility = View.GONE
                bt_buscar.visibility = View.VISIBLE
                bt_actualizar.visibility = View.GONE
            }

        }

    }
}