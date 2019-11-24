package com.aur3liux.waleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.MenuItem
import android.widget.Toast
import com.aur3liux.waleapp.model.AdminDb
import com.aur3liux.waleapp.model.Usuario
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    val adminDb = AdminDb()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Le damos el foco al dato del correo electronico
        txCorreoLogin.requestFocus()
        crearcuenta_Click()
        iniciarSesion_Click()
    }

    fun iniciarSesion_Click(){
        btnIniciarSesion.setOnClickListener{
            if(capturaCorrecta()){
                db.collection("Usuarios").document(txCorreoLogin.text.toString())
                    .get().addOnSuccessListener { document ->
                        if(document.exists()){
                            //Verificamos que coincida el password
                            val pwd = document.data!!.get("Password")
                            if(pwd!!.equals(txPasswordLogin.text.toString())){
                                //Las credenciales de acceso son correctas
                                val user = Usuario(
                                    txCorreoLogin.text.toString(),
                                    document.data!!.get("Telefono").toString(),
                                    document.data!!.get("Nombre").toString(),
                                    pwd.toString())
                                //Guardamos localmente en SQLite
                                adminDb.insertUsuario(user)
                                //Si no hay error abrimos la pantalla principal
                                val iPrincipal = Intent(applicationContext, MainActivity::class.java)
                                startActivity(iPrincipal)

                            }else{
                                Toast.makeText(applicationContext,"Error al iniciar sesion",Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            Toast.makeText(applicationContext,"Error al iniciar sesion",Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

    //Abrir la pantalla para crear cuenta
    fun crearcuenta_Click(){
        btnRegistro.setOnClickListener(){
            val iRegistro = Intent(applicationContext,Registro::class.java)
            startActivity(iRegistro)
            overridePendingTransition(R.animator.from_rigth,R.animator.to_left)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.animator.from_left,R.animator.to_right)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> {
                finish()
                overridePendingTransition(R.animator.from_left,R.animator.to_right)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    //Validar que la captura de datos esté correcta - verdadero captura correcta, falso ceptura tuvo error
    fun capturaCorrecta():Boolean{
        var valorRetorno = true
        val correo = txCorreoLogin.text.toString()
        val pwd = txPasswordLogin.text.toString()

        if(correo.length == 0){
            Toast.makeText(applicationContext,"No deje el correo vacío", Toast.LENGTH_SHORT).show()
            valorRetorno = false
        }else if(pwd.length == 0){
            Toast.makeText(applicationContext,"No deje el password vacío", Toast.LENGTH_SHORT).show()
            valorRetorno = false
        }
        return valorRetorno
    }
}
