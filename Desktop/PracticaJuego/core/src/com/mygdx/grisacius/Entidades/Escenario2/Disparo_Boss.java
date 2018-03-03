package com.mygdx.grisacius.Entidades.Escenario2;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.grisacius.Tools.CompruebaColisiones;

/**
 * Created by juana on 11/02/2018.
 */

public class Disparo_Boss {


        private Texture texture;
        public static  int ANCHO_DISPARO = 50;
        public static  int ALTO_DISPARO = 50;
        public static int VELOCIDAD_DISPARO_BOSS=800;


        float x, y;
        CompruebaColisiones colision;
        public boolean remove = false;

        public Disparo_Boss(Boss boss) {
            this.y = boss.y;
            this.x = boss.x;
            this.colision = new CompruebaColisiones(x, y, ANCHO_DISPARO, ALTO_DISPARO);

            if (texture == null)
                texture = new Texture("imagenes/disparo_boss.png");
        }


    public void update(float deltaTime) {
            x -= VELOCIDAD_DISPARO_BOSS * deltaTime;
            if (x < 0)
                remove = true;


            colision.mover(x, y);
        }

        public void render(SpriteBatch batch) {
            batch.draw(texture, x, y,ANCHO_DISPARO,ALTO_DISPARO);
        }

        public CompruebaColisiones getColision() {
            return colision;
        }


}
