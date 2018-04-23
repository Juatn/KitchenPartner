package com.example.juan.kitchenpartner


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.analytics.FirebaseAnalytics


class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val  mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        val btnEntrar=findViewById<Button>(R.id.buttonEntrar) as Button
        val btnRegistro=findViewById<Button>(R.id.buttonRegistro) as Button

        btnEntrar.setOnClickListener(){

        }

        btnRegistro.setOnClickListener(){
            this.closeContextMenu()
            this.setContentView(R.layout.activity_registro)

        }


    }





}
