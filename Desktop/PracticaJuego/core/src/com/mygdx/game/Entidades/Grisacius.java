package com.mygdx.game.Entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MainGame;
import com.mygdx.game.Tools.CompruebaColisiones;

import static com.mygdx.game.Constantes.ALTO_PANTALLA;
import static com.mygdx.game.Constantes.ANCHO_PANTALLA;
import static com.mygdx.game.Constantes.DEFAULT_X;


/**
 * Created by juana on 11/02/2018.
 */

public class Grisacius {

        private static Texture texture;
    public static int GRISACIUS_ANCHO_PIXEL = 32;
    public static int GRISACIUS_ALTO_PIXEL = 32;
    public static int GRISACIUS_ANCHO = GRISACIUS_ANCHO_PIXEL * 3;
    public static int GRISACIUS_ALTO = GRISACIUS_ALTO_PIXEL * 3;
    public static float VELOCIDAD_GRISACIUS = 800;
    public static float TIEMPO_DISPARO = 0.3f;


        float x, y;

        CompruebaColisiones colision;
        public boolean remove = false;
        public boolean haComido=false;

        public Grisacius() {
            this.y = ALTO_PANTALLA/2;
            this.x = 60f;
            this.colision = new CompruebaColisiones(y, x, GRISACIUS_ALTO, GRISACIUS_ANCHO);

            if (texture == null)
                texture = new Texture("gato.png");
        }

        public void update(float deltaTime) {
            if (isUP()) {// ARRIBA
                y += VELOCIDAD_GRISACIUS * Gdx.graphics.getDeltaTime();

                if (y > ALTO_PANTALLA)
                    y = ALTO_PANTALLA - texture.getHeight();
            }
            if (isDown()) {//ABAJO
                y -= VELOCIDAD_GRISACIUS * Gdx.graphics.getDeltaTime();

                if (y < 0)
                    y = 0;
            }



            colision.mover(x, y);
        }


        public void render(SpriteBatch batch) {
            batch.draw(texture, x, y, GRISACIUS_ANCHO, GRISACIUS_ALTO);
        }

        public CompruebaColisiones getColision() {
            return colision;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }


    public static boolean isUP() {
        return Gdx.input.isKeyPressed(Input.Keys.UP) || (Gdx.input.isTouched() && MainGame.cam.getInputInGameWorld().y < ALTO_PANTALLA / 2);
    }

    public static boolean isDown() {
        return Gdx.input.isKeyPressed(Input.Keys.DOWN) || (Gdx.input.isTouched() && MainGame.cam.getInputInGameWorld().y >= ALTO_PANTALLA / 2);
    }
}
