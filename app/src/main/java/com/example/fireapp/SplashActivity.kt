package com.example.fireapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    //Deklarasi berapa lama timer splash screen muncul

    private val SPLASH_TIME_OUT:Long = 3000 //delay 3 detik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //kode untuk menjalankan main screen ketika waktu splash screen habis

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}