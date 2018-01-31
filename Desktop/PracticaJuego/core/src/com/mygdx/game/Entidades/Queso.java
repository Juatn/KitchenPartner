package com.mygdx.game.Entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Tools.CompruebaColisiones;

/**
 * Created by juana on 31/01/2018.
 */

public class Queso {
    public static int ALTO_QUESO=100;
    public static int ANCHO_QUESO=80;
    private static Texture texture;
    public float x,y;
    public CompruebaColisiones colision;
    public boolean remove=false;


    public Queso(float y){

        this.x=1;
        this.y=y;

        if (texture == null)
            texture = new Texture("queso.png");
    }

    public void update(float deltaTime,Rata rat){

        if (rat.getX() < -rat.ANCHO_RATA)
            remove = true;
    }
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, ANCHO_QUESO, ALTO_QUESO);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }



}
