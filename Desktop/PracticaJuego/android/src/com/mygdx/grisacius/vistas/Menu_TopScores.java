package com.mygdx.grisacius.vistas;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mygdx.grisacius.MainGame;
import com.mygdx.grisacius.R;
import com.mygdx.grisacius.bdd.GrisaciusAndroidOpenHelper;
import com.mygdx.grisacius.bdd.GrisaciusDataBaseAndroid;

import static com.mygdx.grisacius.MainGame.dataBase;

/**
 * Created by juana on 06/03/2018.
 */

public class Menu_TopScores extends AppCompatActivity {


    private Button botonSalida;
    private ImageView fotoScores;
    private EditText txtScore;
    private GrisaciusDataBaseAndroid basedatos;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topscores);

        //asignar variables
        basedatos = new GrisaciusDataBaseAndroid(this);

        botonSalida = (Button) findViewById(R.id.btnVolverMenu);
        fotoScores = (ImageView) findViewById(R.id.imgScore);
        txtScore = (EditText) findViewById(R.id.txtScores);

        txtScore.setEnabled(false);


        botonSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverMenu();

            }
        });

        mostrarScores();


    }

    public void volverMenu() {
        Intent i = new Intent(getApplicationContext(), Menu_Principal.class);


        startActivity(i);
    }

    public void mostrarScores() {


        if (basedatos.getTop3Games() != null) {

            for (int i = 0; i < basedatos.getTop3Games().length; i++) {
                this.txtScore.setText(basedatos.getTop3Games()[i].toString());
            }
        } else {
            this.txtScore.setText("No hay ninguna partida jugada aun");

        }


    }
}
