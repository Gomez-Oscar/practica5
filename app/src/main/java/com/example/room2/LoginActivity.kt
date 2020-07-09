package com.example.room2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.room2.usuario.UsuarioDAO
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bt_crear_cuenta.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
            finish()
        }

        bt_iniciar_sesion.setOnClickListener {

            val correo = et_correo_electronico.text.toString()
            val contrasena = et_password.text.toString()

            val usuarioDAO: UsuarioDAO = ROOM2.database2.UsuarioDAO()
            val usuario = usuarioDAO.buscarUsuario(correo)

            if (correo.isEmpty() || correo.isBlank()) {
                Toast.makeText(this, "Ingrese un correo electrónico", Toast.LENGTH_SHORT).show()

            } else if (contrasena.isBlank() || contrasena.isEmpty()) {
                Toast.makeText(this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show()

            } else if (usuario != null) {
                if (correo == usuario.correo) {
                    if (contrasena == usuario.contrasena) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Contraseña invalida", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Correo no registrado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}