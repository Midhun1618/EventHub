package com.voxcom.eventhub

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.voxcom.eventhub.utils.UserStorage

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val userStorage = UserStorage(this)

            val loggedInUser = userStorage.getAllUsers().find { it.isLoggedIn }

            if (loggedInUser != null) {
                GlobalData.userName = loggedInUser.u
                GlobalData.eMail = loggedInUser.e

                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }

            finish()
        }, 2000)
    }
}
