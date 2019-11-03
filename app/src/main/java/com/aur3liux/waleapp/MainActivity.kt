package com.aur3liux.waleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adminTabBar()
    }

    //Detectamos cuando se pulse una u otra opcion del TabBar
    fun adminTabBar(){
        val fSingle = FragmentSingle()
        val fGroup = FragmentGroup()
        //Mostramos el fragment individual por default
        mostrarFragment(fSingle)

        tabOptions.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.action_single ->{
                    mostrarFragment(fSingle)
                }
                R.id.action_group -> {
                   mostrarFragment(fGroup)
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
