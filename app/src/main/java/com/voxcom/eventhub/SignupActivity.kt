package com.voxcom.eventhub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.voxcom.eventhub.models.User
import com.voxcom.eventhub.utils.UserStorage

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)

        val emailInput = findViewById<EditText>(R.id.email)
        val usernameInput = findViewById<EditText>(R.id.username)
        val passwordInput = findViewById<EditText>(R.id.password)
        val signupButton = findViewById<Button>(R.id.loginBtn)

        signupButton.setOnClickListener {
            val userStorage = UserStorage(this)
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val username = usernameInput.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty()) {
                if (userStorage.isEmailRegistered(email)) {
                    Toast.makeText(this, "Email already registered!", Toast.LENGTH_SHORT).show()
                } else {

                    val newUser = User(
                        u = username,
                        e = email,
                        p = password,
                        isLoggedIn = true,
                        selectedEvents = mutableListOf(),
                        tasks = mutableListOf()
                    )

                    userStorage.saveUser(newUser)

                    GlobalData.userName = username
                    GlobalData.eMail = email

                    Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
