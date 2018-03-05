package com.mygdx.grisacius.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.mygdx.grisacius.Constantes;
import com.mygdx.grisacius.R;

/**
 * Created by juana on 23/02/2018.
 */

public class Menu_Ajustes extends AppCompatActivity {

    private RadioButton ingles;
    private RadioButton castellano;
    private RadioButton facil;
    private RadioButton media;
    private RadioButton dificil;
    private Button btnGuardar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);


        // asignar variables
        ingles = (RadioButton) findViewById(R.id.radioIngles);
        castellano = (RadioButton) findViewById(R.id.radioCastellano);
        facil = (RadioButton) findViewById(R.id.radioFacil);
        media = (RadioButton) findViewById(R.id.radioMedia);
        dificil = (RadioButton) findViewById(R.id.radioDificil);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dificultad();
                Intent i = new Intent(getApplicationContext(), Menu_Principal.class);


                startActivity(i);


            }
        });
    }

    public void idioma() {

    }


    public void enIngles() {

    }

    public void enEsp() {


    }


    public void dificultad() {
        if (dificil.isSelected()) {

            Constantes.MIN_RATAS = 0.3f;
            Constantes.MAX_RATAS = 0.2F;
            Constantes.MIN_VELOCIDAD_RATA = Constantes.MIN_VELOCIDAD_RATA + 500;
            Constantes.MAX_VELOCIDAD_RATA = Constantes.MAX_VELOCIDAD_RATA + 30;

        }
        if (media.isSelected()) {
            Constantes.MIN_RATAS = 1f;
            Constantes.MAX_RATAS = 0.4f;
            Constantes.MIN_VELOCIDAD_RATA = Constantes.MIN_VELOCIDAD_RATA + 30;
            Constantes.MAX_VELOCIDAD_RATA = Constantes.MAX_VELOCIDAD_RATA + 10;
        } else {
            return;
        }

    }

}
