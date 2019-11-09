package com.aur3liux.waleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_login.*

class Registro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        txCorreo.requestFocus()
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
}
