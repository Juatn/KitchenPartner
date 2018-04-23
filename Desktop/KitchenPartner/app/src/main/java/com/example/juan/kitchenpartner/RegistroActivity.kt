package com.example.juan.kitchenpartner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.juan.kitchenpartner.controlador.Utils
import com.example.juan.kitchenpartner.modelo.Usuario
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_registro.*


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
        val  mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        btnRegistrame.setOnClickListener(){

            val centinela: Int =0

            if(checkCondiciones.isChecked){

                val usuario=Usuario()

                if(editNombre.text.isNullOrBlank()){
                    centinela+1

                }
                else if(editCorreo.text.isNullOrBlank()){
                    centinela+1
                }
                else if(editpassword.text.isNullOrBlank()){
                    centinela+1
                }
                else if(!editrepitpassword.text.equals(editpassword.text)||editTextRepitPassword.text.isNullOrBlank()){
                    centinela+1
                }

                if(centinela==0){
                    usuario.s_nombre =editNombre.text.toString();
                    usuario.s_correo =editCorreo.text.toString();
                    usuario.s_password =editpassword.text.toString();

                    // AGREGAR A LA BDD
                    Utils.usuarios.add(usuario);
                    val bundle = Bundle()
                    bundle.putString(FirebaseAnalytics.Param.ITEM_ID, usuario.s_id_usuario)
                    bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, usuario.s_nombre)
                    bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, usuario.s_correo)
                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

                }


            }
            else{
                Toast.makeText(this, "Debe aceptar primero nuestros terminos y condiciones.",
                        Toast.LENGTH_LONG).show();
            }


        }




    }





}
