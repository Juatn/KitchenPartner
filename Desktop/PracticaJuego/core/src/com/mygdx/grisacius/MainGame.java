package com.mygdx.grisacius;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.grisacius.Tools.GameCamera;
import com.mygdx.grisacius.Ventanas.GameScreen;
import com.mygdx.grisacius.Ventanas.GameScreenPrimerBoss;
import com.mygdx.grisacius.Ventanas.IntroScreen;
import com.mygdx.grisacius.bdd.GrisaciusDataBase;
import com.mygdx.grisacius.bdd.GrisaciusScores;

import static com.mygdx.grisacius.Constantes.ALTO_PANTALLA;
import static com.mygdx.grisacius.Constantes.ANCHO_PANTALLA;

public class MainGame extends Game {


    public static boolean IS_MOBILE = false;
    public static GrisaciusDataBase dataBase;


    public MainGame( GrisaciusDataBase dataBase){

        this.dataBase=dataBase;

    }



    public static GameCamera cam;

    @Override
    public void create() {




        cam = new GameCamera(ANCHO_PANTALLA, ALTO_PANTALLA);


        if (Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS)
            IS_MOBILE = true;
        IS_MOBILE = true;



        this.setScreen(new IntroScreen(this));
    }

    @Override
    public void render() {

        super.render();
    }

    @Override
    public void resize(int width, int height) {
        cam.update(width, height);
        super.resize(width, height);
    }

    @Override
    public void dispose() {


    }


}