package com.mygdx.grisacius;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.mygdx.grisacius.Entidades.Grisacius.TIEMPO_DISPARO;

/**
 * Created by juana on 23/01/2018.
 */

public class Constantes {

    public static Random random = new Random();


    //  JUEGO

    public static final int ANCHO_PANTALLA = 1280;
    public static final int ALTO_PANTALLA = 720;
    public static float MIN_RATAS = 1f;
    public static float MAX_RATAS = 0.5f;
    public static int SCORE = 0;


    // GRISACIUS


    // DISPAROS
    public static int VELOCIDAD_DISPARO = 1000;
    public static int DEFAULT_X = 65;

    //RATA
    public static int MIN_VELOCIDAD_RATA = 200;
    public static int MAX_VELOCIDAD_RATA = 600;
    public static int VELOCIDAD_QUESO = 200;

    //GAME OVER

    public static int MIN_EMPEZAR = 200;
    public static int MAX_TERMINAR = 800;

    // FONDO ANIMADO
    public static int VELOCIDAD_DEFECTO = 80;
    public static int ACELERACION = 50;
    public static int ACELERACION_OBJETIVO = 200;


    public static int getInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static void dificultad() {
        MIN_RATAS -= 0.001f;
        MIN_VELOCIDAD_RATA += 50f;
        MAX_VELOCIDAD_RATA += 100f;
        VELOCIDAD_QUESO += 50;

        TIEMPO_DISPARO -= 0.00001f;
    }


}
