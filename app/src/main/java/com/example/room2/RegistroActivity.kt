package com.example.room2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

        bt_registrar.setOnClickListener {
            val email = et_correo.text.toString()
            val password = et_contrasena.text.toString()

            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        //crearUsuarioEnBaseDeDatos()
                        Toast.makeText(
                            this, "Registro Exitoso",
                            Toast.LENGTH_SHORT
                        ).show()
                        onBackPressed()
                    } else {
                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun crearUsuarioEnBaseDeDatos() {

    }
}