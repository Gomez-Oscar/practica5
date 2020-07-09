package com.example.room2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.room2.usuario.Usuario
import com.example.room2.usuario.UsuarioDAO
import kotlinx.android.synthetic.main.activity_registro.*
import java.sql.Types.NULL

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        bt_registrar.setOnClickListener {

            val nombre = et_nombre.text.toString()
            val correo = et_correo.text.toString()
            val contrasena = et_contrasena.text.toString()
            val contrasenax2 = et_contrasenax2.text.toString()

            if (nombre.isEmpty() || nombre.isBlank()) {
                Toast.makeText(this, "Ingrese un Nombre", Toast.LENGTH_SHORT).show()

            } else if (correo.isEmpty() || correo.isBlank()) {
                Toast.makeText(this, "Ingrese un Correo Electrónico", Toast.LENGTH_SHORT).show()

            } else if (contrasena.isEmpty() || contrasena.isBlank()) {
                Toast.makeText(this, "Ingrese una Contraseña", Toast.LENGTH_SHORT).show()

            } else if (contrasena.length < 6) {
                Toast.makeText(this, "Mínimo 6 dígitos", Toast.LENGTH_SHORT).show()

            } else if (contrasena != contrasenax2) {
                Toast.makeText(this, "Las contraseñan NO coinciden", Toast.LENGTH_SHORT).show()

            } else {

                val usuario = Usuario(NULL, nombre, correo, contrasena)
                val usuarioDAO: UsuarioDAO = ROOM2.database2.UsuarioDAO()

                usuarioDAO.crearUsuario(usuario)

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()

            }

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}