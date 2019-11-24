package com.aur3liux.waleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_preguntas.*

class Preguntas : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    lateinit var listaPalabras: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preguntas)
        obtieneListaPalabras()
    }

    //Baja de la nuba la lista de palabras con las que se van a jugar
    fun obtieneListaPalabras(){
        db.collection("Mimicas").document("Canciones").collection("canciones")
            .get().addOnSuccessListener {palabras ->
                for(word in palabras){
                    //listaPalabras.add(word.toString())
                    Log.i("PALABRAS",word.id)
                }
                //txPreguntaReto.setText(listaPalabras!!.get(0))
            }
    }
}
