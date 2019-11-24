package com.aur3liux.waleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.MenuItem
import android.widget.Toast
import com.aur3liux.waleapp.model.AdminDb
import com.aur3liux.waleapp.model.Usuario
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_registro.*

class Registro : AppCompatActivity() {

    val adminDb = AdminDb()
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        txCorreo.requestFocus()
        crearUsuario()
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

    fun crearUsuario(){
        txNombre.setText("Aurelio")
        txCorreo.setText("aureliux@hotmail.com")
        txTelefono.setText("9811125246")
        txPassword.setText("12345678")
        txPasswordConfirm.setText("12345678")
        btnGuardar.setOnClickListener(){
            if(capturCorrecta()) {
                //Creamos el objeto de la clase Usario
                val user = Usuario(
                    txCorreo.text.toString(),
                    txTelefono.text.toString(),
                    txNombre.text.toString(),
                    txPassword.text.toString()
                )

                //Guardamos en la nube
                val usuarioHash: HashMap<String, String> = hashMapOf(
                    "Nombre" to user.nombre,
                    "Telefono" to user.telefono,
                    "Password" to user.pwd
                )

                //Verificar que el correo no existe en la nube
                db.collection("Usuarios").document(user.correo)
                    .get().addOnSuccessListener { document ->
                        if(document.exists()){
                            Toast.makeText(applicationContext,"El correo ya existe",Toast.LENGTH_SHORT).show()
                        }
                        else{
                            //Guardamos el usuario
                            db.collection("Usuarios").document(user.correo)
                                .set(usuarioHash).addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        //Guardamos localmente en SQLite
                                        adminDb.insertUsuario(user)
                                        //Si no hay error abrimos la pantalla principal
                                        val iPrincipal = Intent(applicationContext, MainActivity::class.java)
                                        startActivity(iPrincipal)
                                    } else {
                                        Toast.makeText(
                                            applicationContext,
                                            "Error al guardar",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        }
                }

            }
        }
    }

    //Validar que la captura de datos esté correcta - verdadero captura correcta, falso ceptura tuvo error
    fun capturCorrecta():Boolean{
        var valorRetorno = true

        val telefono = txTelefono.text.toString()
        val correo = txCorreo.text.toString()
        val nombre = txNombre.text.toString()
        val pwd = txPassword.text.toString()
        val pwdConfirm = txPasswordConfirm.text.toString()

        if(telefono.length == 0){
            Toast.makeText(applicationContext,"No deje el teléfono vacío", Toast.LENGTH_SHORT).show()
            valorRetorno = false
        } else if(telefono.length != 10){
            Toast.makeText(applicationContext,"El teléfono debe tener 10 dígitos", Toast.LENGTH_SHORT).show()
            valorRetorno = false
        } else if(correo.length == 0){
            Toast.makeText(applicationContext,"No deje el correo vacío", Toast.LENGTH_SHORT).show()
            valorRetorno = false
        }else if(!isValidEmail(correo)){
            Toast.makeText(applicationContext,"No es un correo electrónico válido", Toast.LENGTH_SHORT).show()
            valorRetorno = false
        }else if(nombre.length == 0){
            Toast.makeText(applicationContext,"No deje el nombre vacío", Toast.LENGTH_SHORT).show()
            valorRetorno = false
        }else if(pwd.length == 0){
            Toast.makeText(applicationContext,"No deje el password vacío", Toast.LENGTH_SHORT).show()
            valorRetorno = false
        }else if(pwd.length < 8){
            Toast.makeText(applicationContext,"Al menos el password debe tener 8 caracteres", Toast.LENGTH_SHORT).show()
            valorRetorno = false
        } else if(!pwd.equals(pwdConfirm)){
            Toast.makeText(applicationContext,"El password y la confirmacion no coinciden", Toast.LENGTH_SHORT).show()
            valorRetorno = false
        }
        return valorRetorno
    }

    private fun isValidEmail(email: String):Boolean {
        val pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
