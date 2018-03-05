package com.mygdx.grisacius.vistas;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.mygdx.grisacius.AndroidLauncher;
import com.mygdx.grisacius.MainGame;
import com.mygdx.grisacius.R;


/**
 * Created by juana on 23/02/2018.
 */

public class Menu_Principal extends AppCompatActivity {

    private Button btnContinuar;
    private Button btnNuevaPartida;
    private Button btnAjustes;
    private Button btnSalir;
    private int dificultad;
    private ImageView logo;
    private ImageView rata;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //asignacion variables
        btnContinuar=(Button)findViewById(R.id.btnContinuar);
        btnNuevaPartida=(Button)findViewById(R.id.btnNuevaPartida);
        btnAjustes=(Button)findViewById(R.id.btnAjustes);
        btnSalir=(Button)findViewById(R.id.btnSalir);
        logo=(ImageView)findViewById(R.id.logo);
        rata=(ImageView)findViewById(R.id.imgrat);



        // continuamos la partida (por hacer)
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                continuar();

            }
        });
        // nueva partida
        btnNuevaPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nuevaPartida();

            }
        });

        // Ajustes
        btnAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ventanaAjustes();



            }
        });

        // boton salir
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                salir();

            }
        });





    }
    public void ventanaAjustes(){

        Intent i = new Intent(getApplicationContext(), Menu_Ajustes.class);

        startActivity(i);
    }
    public void nuevaPartida(){

        Intent i = new Intent(getApplicationContext(), AndroidLauncher.class);



        startActivity(i);

    }

    public void continuar(){
        Intent i = new Intent(getApplicationContext(), AndroidLauncher.class);


        startActivity(i);

    }
    public void salir(){
        System.exit(0);
    }


}
