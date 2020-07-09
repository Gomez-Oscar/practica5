package com.example.room2.ui.delete

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.room2.R
import com.example.room2.ROOM2
import kotlinx.android.synthetic.main.fragment_delete.*

class DeleteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_borrar.setOnClickListener{
            val nombre = et_nombre.text.toString()

            val deudorDAO = ROOM2.database.DeudorDao()
            val deudor = deudorDAO.buscarDeudor(nombre)

            if(deudor != null){
                deudorDAO.borrarDeudor(deudor)
            }else{
                Toast.makeText(context,"Deudor no Encontrado",Toast.LENGTH_SHORT).show()
            }
        }
    }
}