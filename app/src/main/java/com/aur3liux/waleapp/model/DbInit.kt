package com.aur3liux.waleapp.model

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.aur3liux.waleapp.AppWale

class DbInit: SQLiteOpenHelper(AppWale.context,AppWale.DB_NAME,null,AppWale.version){

    var qryCreaUsuario = "CREATE TABLE ${AppWale.tbUsario} (" +
            "${Contract.Usuario.CORREO} TEXT PRIMARY KEY," +
            "${Contract.Usuario.TELEFONO} TEXT," +
            "${Contract.Usuario.NOMBRE}, TEXT," +
            "${Contract.Usuario.PWD} TEXT);"


    var qryCrearReto = "CREATE TABLE ${AppWale.tbReto}(" +
            "${Contract.Reto.ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${Contract.Reto.TEMA} TEXT NOT NULL," +
            "${Contract.Reto.ACIERTOS} INTEGER," +
            "${Contract.Reto.ERRORES} INTEGER," +
            "${Contract.Reto.PUNTOS} INTEGER," +
            "${Contract.Reto.MODALIDAD});"


    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(qryCreaUsuario)
        db!!.execSQL(qryCrearReto)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}