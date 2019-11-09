package com.aur3liux.waleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Le damos el foco al dato del correo electronico
        txCorreo.requestFocus()
        crearcuenta()
    }

    //Abrir la pantalla para crear cuenta
    fun crearcuenta(){
        btnRegistro.setOnClickListener(){
            val iRegistro = Intent(applicationContext,Registro::class.java)
            startActivity(iRegistro)
            overridePendingTransition(R.animator.from_rigth,R.animator.to_left)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.animator.from_left,R.animator.to_right)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> {
                finish()
                overridePendingTransition(R.animator.from_left,R.animator.to_right)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}