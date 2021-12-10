package com.example.registrationactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPassword1: EditText
    private lateinit var buttonSubmit: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextPassword1 = findViewById(R.id.editTextPassword1)
        buttonSubmit = findViewById(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val password1 = editTextPassword1.text.toString().trim()

            if (email.isEmpty()) {
                editTextEmail.error = "Enter your E-mail"
                return@setOnClickListener
            } else if (!(email.contains("@"))) {
                editTextEmail.error = "please enter the correct one "
                return@setOnClickListener
            } else if (password.isEmpty()) {
                editTextPassword.error = "enter your password please"
                return@setOnClickListener
            } else if (!(password.matches("(.*[A-Z]*.)".toRegex())) ||
                !(password.matches("(.*[0-9]*.)".toRegex()) || !(password.matches("(.*[!@#$%^&*()_+]*.)".toRegex())))

            ) {
                editTextPassword.error = "enter the correct password "
                return@setOnClickListener
            } else if (password.length <= 9) {
                editTextPassword.error =
                    "sorry,password is short,it must conatin 9 symbols at least"
                return@setOnClickListener

            } else if (password1.isEmpty()) {
                editTextPassword.error = "try again "
                return@setOnClickListener
            } else if (password != password1) {
                editTextPassword1.error = "passwords should match "
                return@setOnClickListener
            } else
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "registration is done! ", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(this, "registration gone wrong :(", Toast.LENGTH_SHORT)
                                .show()
                        }

                    }


        }
    }
}


