package com.aur3liux.waleapp

import android.app.Application
import android.content.Context
import com.aur3liux.waleapp.model.DbInit

class AppWale: Application() {

    companion object{
        lateinit var context: Context
        lateinit var DB: DbInit
        val DB_NAME = "Wale.db"
        val tbUsario = "usuario"
        val tbTemasGrupal = "temasGrupal"
        val tbItemsGrupal = "itemsGrupal"
        val tbReto = "reto"
        val version = 1

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        DB = DbInit()
    }
}