package com.example.juan.kitchenpartner

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.juan.kitchenpartner.modelo.Usuario

/**
 * Created by juana on 29/03/2018.
 */
class RegistroActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)


        val btnRegistrame=findViewById<Button>(R.id.buttonRegistrame) as Button
        val checkCondiciones=findViewById<CheckBox>(R.id.checkBoxTerminos) as CheckBox
        val editNombre=findViewById<EditText>(R.id.editTextNombre)as EditText
        val editCorreo=findViewById<EditText>(R.id.editTextCorreo)as EditText
        val editpassword=findViewById<EditText>(R.id.editTextPassword)as EditText
        val editrepitpassword=findViewById<EditText>(R.id.editTextRepitPassword)as EditText


        btnRegistrame.setOnClickListener(){

            if(checkCondiciones.isChecked){

                val usuario=Usuario();

                if(editNombre.text.isNullOrBlank()){

                }


            }
            else{
                Toast.makeText(this, "Debe aceptar primero nuestros terminos y condiciones.",
                        Toast.LENGTH_LONG).show();
            }


        }




    }





}
