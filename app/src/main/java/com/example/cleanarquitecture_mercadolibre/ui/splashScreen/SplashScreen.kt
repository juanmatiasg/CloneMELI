package com.example.cleanarquitecture_mercadolibre.ui.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.cleanarquitecture_mercadolibre.R
import com.example.cleanarquitecture_mercadolibre.ui.mainActivity.MainActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            // Este método se ejecutará una vez que termine el temporizador
            // Inicia la actividad principal de tu aplicación

            Intent(this,MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        },5000) /*Tiempo de Espera*/
    }
}