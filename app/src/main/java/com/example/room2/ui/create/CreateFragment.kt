package com.example.room2.ui.create

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.room2.R
import com.example.room2.ROOM2
import com.example.room2.model.Deudor
import com.example.room2.model.DeudorDAO
import kotlinx.android.synthetic.main.fragment_create.*
import java.sql.Types.NULL

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

        bt_guardar.setOnClickListener {
            val nombre = et_nombre.text.toString()
            val telefono = et_telefono.text.toString()
            val cantidad = et_cantidad.text.toString()

            if (nombre.isEmpty() || nombre.isBlank()) {
                Toast.makeText(context, "Ingrese un Nombre", Toast.LENGTH_SHORT).show()

            } else if (telefono.isEmpty() || telefono.isBlank()) {
                Toast.makeText(context, "Ingrese un Teléfono", Toast.LENGTH_SHORT).show()

            } else if (cantidad.isEmpty() || cantidad.isBlank()) {
                Toast.makeText(context, "Ingrese un Cantidad", Toast.LENGTH_SHORT).show()

            } else {

                val deudorDAO: DeudorDAO = ROOM2.database.DeudorDao()
                val deudorBuscar = deudorDAO.buscarDeudor(nombre)

                if (deudorBuscar == null) {
                    val deudor = Deudor(NULL, nombre, telefono, cantidad.toLong())
                    deudorDAO.crearDeudor(deudor)
                } else {
                    val alertDialog: AlertDialog? = activity?.let {
                        val builder = AlertDialog.Builder(it)
                        builder.apply {
                            setMessage("Este nombre ya esta registrado, ingrese un nombre diferente con el fin de identificar mejor a sus deudores")
                            setPositiveButton("Aceptar") { dialog, id -> }
                            //setNegativeButton("Cancelar"){dialog, id ->}
                        }
                        builder.create()
                    }
                    alertDialog?.show()
                }



                et_nombre.setText("")//se limpian los campos de entrada
                et_telefono.setText("")
                et_cantidad.setText("")
            }

        }
    }
}
