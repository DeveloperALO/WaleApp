package com.aur3liux.waleapp.model

import android.content.ContentValues
import android.widget.Toast
import com.aur3liux.waleapp.AppWale

data class Usuario(var correo: String, var telefono: String, var nombre: String, var pwd: String)

class AdminDb {

    //Inserta usuario - Inserta los datos de un nuevo usuario
    fun insertUsuario(user: Usuario){
        try{
            val db = AppWale.DB.writableDatabase
            val qryInsert = "INSERT INTO ${AppWale.tbUsario}(" +
                    "${Contract.Usuario.CORREO}," +
                    "${Contract.Usuario.TELEFONO}," +
                    "${Contract.Usuario.NOMBRE}," +
                    "${Contract.Usuario.PWD}) VALUES(" +
                    "'${user.correo}', " +
                    "'${user.telefono}'," +
                    "'${user.nombre}'," +
                    "'${user.pwd}'); "
            db.execSQL(qryInsert)
            db.close()
        }catch (ex: Exception){
            Toast.makeText(AppWale.context, "Error al guardar al usuario", Toast.LENGTH_SHORT).show()
        }
    }

    //Consultar usuario - Retorna verdadero si hay un usuario registrado, de lo contrario retorna falso
    fun testUsuario():Boolean{
        try{
            val db = AppWale.DB.readableDatabase
            val qryConsulta = "SELECT * FROM ${AppWale.tbUsario}"
            val cursor = db.rawQuery(qryConsulta,null)
            if(cursor.count == 0){
                db.close()
                return false
            }
            else {
                db.close()
                return true
            }
        }catch (ex: Exception){
            Toast.makeText(AppWale.context, "Error recuperar dato", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    //Insertar Reto
    //Consulta Reto
}