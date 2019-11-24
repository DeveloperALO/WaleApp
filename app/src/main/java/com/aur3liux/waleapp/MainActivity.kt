package com.aur3liux.waleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.aur3liux.waleapp.model.AdminDb

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val adminDb = AdminDb()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adminTabBar()
    }

    //Detectamos cuando se pulse una u otra opcion del TabBar
    fun adminTabBar(){
        val fMimicas = Fragment_mimicas()
        val fPistas = Fragment_pistas()
        val fPreguntas = Fragment_preguntas()
        //Mostramos el fragment individual por default
        mostrarFragment(fMimicas)

        tabOptions.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.action_mimicas ->{
                    mostrarFragment(fMimicas)
                }
                R.id.action_preguntas -> {
                    //Si ya inicion sesion
                    if(adminDb.testUsuario())
                        mostrarFragment(fPreguntas)
                }
                R.id.action_pistas -> {
                    //Si ya inicio sesion
                    if(adminDb.testUsuario())
                        mostrarFragment(fPistas)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    //Cambia el fragment activo por uno nuevo
    fun mostrarFragment(frag:Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame,frag)
        transaction.commit()
    }



}
