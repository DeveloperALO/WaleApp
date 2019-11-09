package com.aur3liux.waleapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_single.view.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentSingle : Fragment() {

    lateinit var mView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_single, container, false)
        abrirlogin()
        return mView
    }

    //Abrir la ventana de login
    fun abrirlogin(){
        mView.btnLoginRegistroInicial.setOnClickListener(){
            val iLogin = Intent(AppWale.context,Cronometro::class.java)
            startActivity(iLogin)
            activity!!.overridePendingTransition(R.animator.from_rigth,R.animator.to_left)
        }
    }

}
