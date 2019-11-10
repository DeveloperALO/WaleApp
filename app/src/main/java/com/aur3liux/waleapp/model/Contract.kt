package com.aur3liux.waleapp.model

import android.provider.BaseColumns

class Contract {

    //Estructura de la tabla usuario
    class Usuario:BaseColumns{
        companion object{
            val ID = "id"
            val CORREO = "correo"
            val TELEFONO = "telefono"
            val NOMBRE = "nombre"
            val PWD = "pwd"
        }
    }

    //estructura de la tabla reto
    class  Reto:BaseColumns{
        companion object{
            val ID = "id"
            val TEMA = "tema"
            val ACIERTOS = "aciertos"
            val ERRORES = "errores"
            val PUNTOS = "puntos"
            val MODALIDAD = "modalidad"
        }
    }
}