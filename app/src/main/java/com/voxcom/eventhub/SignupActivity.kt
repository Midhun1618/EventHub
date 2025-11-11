package com.voxcom.eventhub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
        val loginButton = findViewById<Button>(R.id.loginBtn)



        loginButton.setOnClickListener {
            val userStorage = UserStorage(this)
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val username = usernameInput.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty()){
                if(userStorage.isEmailRegistered(email)){
                    Toast.makeText(this, "Email is already existing", Toast.LENGTH_SHORT).show()
                } else {
                    GlobalData.userName = username
                    GlobalData.eMail = email
                    userStorage.saveUser(User(username, email, password))

                    val sharedPrefs = getSharedPreferences("UserData", MODE_PRIVATE)
                    val allData = sharedPrefs.all
                    println("Saved SharedPreferences data: $allData")
                    Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }else{
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}