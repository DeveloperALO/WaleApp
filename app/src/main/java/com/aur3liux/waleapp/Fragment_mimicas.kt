package com.aur3liux.waleapp

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.Toast
import com.aur3liux.waleapp.model.AdminDb
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_mimicas.view.*


class Fragment_mimicas : Fragment() {

    lateinit var mView: View
    var testSesion: Boolean = false
    val adminDb = AdminDb()
    var temas = arrayListOf<String>()
    val adapter = ArrayAdapter(AppWale.context,android.R.layout.simple_list_item_1,temas!!)
    val dbFireStore = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_mimicas, container, false)

        testSesion = adminDb.testUsuario()
        //Si ya inicio sesion
        if(testSesion){
            setHasOptionsMenu(true)
            muestraPantallaPrincipal()
            crearListaSnapShot()
        }else{
            ocultaPantallaPrincipal()
        }

        abrirlogin()
        return mView
    }
    fun crearListaSnapShot(){
        dbFireStore.collection("Mimicas")
            .get()
            .addOnSuccessListener{result ->
                adapter.clear()
                for(dc in result){
                    temas.add(dc.id)
                }
                mView.listMimicas.adapter = adapter
                adapter.setNotifyOnChange(true)

                //Al seleccionar un elemento de la lista nos lleve al cronometro
                mView.listMimicas.onItemClickListener = AdapterView.OnItemClickListener{adapterView, view, i, l ->
                    //Guardamos el tema en una variable global para su posterior consulta en la nube
                    AppWale.tema = temas.get(i).toString()
                    val iCronometro = Intent(AppWale.context,Cronometro::class.java)
                    startActivity(iCronometro)
                }
            }
            .addOnFailureListener{e ->
                Toast.makeText(AppWale.context,"Error al descargar los datos", Toast.LENGTH_SHORT).show()
            }
    }


    //Crear lista
    fun crearListaRealTime(){
        dbFireStore.collection("Mimicas").addSnapshotListener{snapShop, e ->
            if(e != null){
                Toast.makeText(AppWale.context,"No se pudo conectar a la base de datos", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            adapter.clear()
            for(dc in snapShop!!.documentChanges){
                temas.add(dc.document.id)
            }
            mView.listMimicas.adapter = adapter
        }
    }

    //Abrir la ventana de login
    fun abrirlogin(){
        mView.btnLoginRegistroInicial.setOnClickListener(){
            val iLogin = Intent(AppWale.context,Login::class.java)
            startActivity(iLogin)
            activity!!.overridePendingTransition(R.animator.from_rigth,R.animator.to_left)
        }
    }


    //Si hay usuario y por lo tanto ya hay un inicio de sesion
    fun muestraPantallaPrincipal(){
        mView.listMimicas.visibility = View.VISIBLE
        mView.btnLoginRegistroInicial.visibility = View.INVISIBLE
    }

    //No hay usuario y por lo tanto no ha iniciado sesion
    fun ocultaPantallaPrincipal(){
        mView.listMimicas.visibility = View.INVISIBLE
        mView.btnLoginRegistroInicial.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.menu_list,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_update -> {
                crearListaSnapShot()
                return true
            }else -> return super.onOptionsItemSelected(item)
        }

    }
}

