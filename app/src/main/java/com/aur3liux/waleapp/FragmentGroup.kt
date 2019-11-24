package com.aur3liux.waleapp


import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aur3liux.waleapp.model.AdminDb
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_group.view.*
import kotlinx.android.synthetic.main.fragment_single.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentGroup : Fragment() {
    lateinit var mView: View
    var temasGroup = arrayListOf<String>()
    val adapter = ArrayAdapter(AppWale.context,android.R.layout.simple_list_item_1,temasGroup!!)
    val dbFireStore = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_group, container, false)
        crearListaSnapShot()
        return mView
    }

    fun crearListaSnapShot(){
        dbFireStore.collection("Grupal").get()
            .addOnSuccessListener{result ->
                adapter.clear()
                for(dc in result){
                    temasGroup.add(dc.id)
                }
                mView.listGroup.adapter = adapter
                adapter.setNotifyOnChange(true)
            }
            .addOnFailureListener{e ->
                Toast.makeText(AppWale.context,"Error al descargar los datos", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.menu_list,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_update -> {
                crearListaSnapShot()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
