package com.example.parcialcomputo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_datos.*

class Datos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos)

/* crea el boton donde se guardaran los datos y se enviaran a la base de datos,
se crea una autetificacion para que no se envien datos nulos

 */
        btnSave.setOnClickListener {
            if(productos.text.isEmpty()||cant.text.isEmpty()||nombre.text.isEmpty()){
                Toast.makeText(this, " Favor rellenar todos los campos", Toast.LENGTH_SHORT).show()
            }
            else{
                var name: String=nombre.text.toString()
                var product: String=productos.text.toString()
                var cantidad: String=cant.text.toString()
                guardarbasededatos(name,cantidad,product)
                Toast.makeText(this,"tu pedido ha sido enviado",Toast.LENGTH_LONG).show()
                nombre.setText("")
                cant.setText("")
                productos.setText("")
            }
        }


    }
    //Se crea una función que mande los datos obtenidos a la base de datos
    fun guardarbasededatos(nombre:String,cantidad:String,producto:String){
        val shop=HashMap<String,String>()
        shop["nombre"]=nombre.toString()
        shop["cantidad"]=cantidad.toString()
        shop["producto"]=producto.toString()
        val rootRef= FirebaseDatabase.getInstance().reference
        val taskRef= rootRef.child("inventario").push()
        taskRef.setValue(shop)

    }
    //se crea una funcion donse se crea el menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.mymenu, menu)
        return true
    }
    //Se crea la funcion para que podamos utilizar el menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.users -> {
                val intent = Intent(this, Integrantes::class.java).apply {

                }
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}