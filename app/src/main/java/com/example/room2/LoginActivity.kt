package com.example.room2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    //se va a configurar que cuando un usuario se logee y salga de la app, cuando entre nuevamente
    // aparezca en el mainactivity con su usuario ya logeado
    override fun onStart() {
        super.onStart()
        val user = mAuth.currentUser
        if (user != null)
            goToMainActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bt_crear_cuenta.setOnClickListener {
            goToRegistroActivity()
        }

        bt_iniciar_sesion.setOnClickListener {
            val email = et_correo_electronico.text.toString()
            val password = et_password.text.toString()
            signInWithFirebase(email, password)
        }

    }

    private fun signInWithFirebase(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    goToMainActivity()
                } else {
                    showMessage("Authentication failed.")
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                }
            }
    }

    //single responsability => cada función debe tener una sola responsabilidad
    private fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun goToRegistroActivity() {
        startActivity(Intent(this, RegistroActivity::class.java))
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}