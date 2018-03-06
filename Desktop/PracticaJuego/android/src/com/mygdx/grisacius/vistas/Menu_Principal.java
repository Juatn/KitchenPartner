package com.mygdx.grisacius.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.mygdx.grisacius.AndroidLauncher;
import com.mygdx.grisacius.R;


/**
 * Created by juana on 23/02/2018.
 */

public class Menu_Principal extends AppCompatActivity {

    private Button btnTopScores;
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
        btnTopScores =(Button)findViewById(R.id.btnTopScores);
        btnNuevaPartida=(Button)findViewById(R.id.btnNuevaPartida);
        btnAjustes=(Button)findViewById(R.id.btnAjustes);
        btnSalir=(Button)findViewById(R.id.btnSalir);
        logo=(ImageView)findViewById(R.id.logo);
        rata=(ImageView)findViewById(R.id.imgrat);




        btnTopScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                topScores();

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

    public void topScores(){


        Intent x = new Intent(getApplicationContext(), Menu_TopScores.class);

        startActivity(x);


    }
    public void salir(){
        System.exit(0);
    }


}
