package com.voxcom.eventhub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.voxcom.eventhub.utils.UserStorage

object GlobalData {
    var userName: String? = "Na"
    var eMail: String? = "Na"
}

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val emailInput = findViewById<EditText>(R.id.email)
        val passwordInput = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.loginBtn)
        val signup = findViewById<TextView>(R.id.signupLink)

        val userStorage = UserStorage(this)

        signup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val user = userStorage.validateUser(email, password)

                if (user != null) {
                    user.isLoggedIn = true
                    userStorage.updateUser(user)

                    GlobalData.userName = user.u
                    GlobalData.eMail = user.e

                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()

                    val sharedPrefs = getSharedPreferences("UserData", MODE_PRIVATE)
                    val allData = sharedPrefs.all
                    Log.d("SharedPrefDebug", "Saved SharedPreferences data: $allData")
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
