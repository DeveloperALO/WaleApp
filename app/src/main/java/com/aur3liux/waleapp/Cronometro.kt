package com.aur3liux.waleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cronometro.*
import android.os.CountDownTimer
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.media.MediaPlayer
import android.widget.Toast
import java.util.*


class Cronometro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cronometro)
        activaCronometro_Click()
    }

    fun activaCronometro_Click(){
        btnInitCronometro.setOnClickListener() {
            object : CountDownTimer(5000, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    txSegundos.setText(
                        String.format(
                            Locale.getDefault(),
                            "%d Seg.", millisUntilFinished / 1000))
                    val sound = MediaPlayer.create(applicationContext,R.raw.clip)
                    sound.start()
                }


                override fun onFinish() {
                    val sound = MediaPlayer.create(applicationContext,R.raw.alarma)
                    sound.start()
                    //Invocamos a las preguntas
                    val iPreguntas = Intent(applicationContext,Preguntas::class.java)
                    startActivity(iPreguntas)
                }
            }.start()
        }
    }
}
