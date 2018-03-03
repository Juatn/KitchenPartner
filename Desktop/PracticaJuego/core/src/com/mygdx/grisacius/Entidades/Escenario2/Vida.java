package com.mygdx.grisacius.Entidades.Escenario2;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.grisacius.Entidades.MoldeRat;
import com.mygdx.grisacius.Tools.CompruebaColisiones;

/**
 * Created by juana on 01/03/2018.
 */

public class Vida   {
    private Texture texture;
    private float x,y;
    public static  int ANCHO_VIDA = 50;
    public static  int ALTO_VIDA = 50;



    public Vida(float x){
        this.x=x;
        this.y=620;
        if(texture==null){
            texture=new Texture("imagenes/vida.png");
        }
    }
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, ANCHO_VIDA, ALTO_VIDA);
    }



    public float getX() {
        return x;
    }


    public float getY() {
        return y;
    }
}
