package com.aur3liux.waleapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aur3liux.waleapp.model.AdminDb
import kotlinx.android.synthetic.main.fragment_single.view.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentSingle : Fragment() {

    lateinit var mView: View
    var testSesion: Boolean = false
    val adminDb = AdminDb()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_single, container, false)

        testSesion = adminDb.testUsuario()
        if(testSesion){
            muestraPantallaPrincipal()
        }else{
            ocultaPantallaPrincipal()
        }

        abrirlogin()
        return mView
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
        mView.txTemporal.visibility = View.VISIBLE
        mView.btnLoginRegistroInicial.visibility = View.INVISIBLE
    }

    //No hay usuario y por lo tanto no ha iniciado sesion
    fun ocultaPantallaPrincipal(){
        mView.txTemporal.visibility = View.INVISIBLE
        mView.btnLoginRegistroInicial.visibility = View.VISIBLE
    }

}
