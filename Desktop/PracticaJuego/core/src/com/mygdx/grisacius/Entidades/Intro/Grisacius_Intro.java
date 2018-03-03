package com.mygdx.grisacius.Entidades.Intro;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.mygdx.grisacius.Constantes.MAX_TERMINAR;
import static com.mygdx.grisacius.Constantes.MIN_EMPEZAR;

/**
 * Created by juana on 21/02/2018.
 */

public class Grisacius_Intro {


    private static Texture texture;
    public  int ancho = 100;
    public  int alto = 100;
    public boolean derecha;


    public float x, y;
    public int velocidad;




    public Grisacius_Intro(float y) {
        this.y = y;
        this.x = 200;
        velocidad=100;
        if(texture==null){
            texture=new Texture("imagenes/gato.png");
        }


    }

    public void update(float deltaTime) {


        if(x>=MAX_TERMINAR){
            derecha=false;
        }
        if(x<=MIN_EMPEZAR){
            derecha=true;
        }


        if(derecha){
            derecha(deltaTime);
        }
        if(!derecha){
            izquierda(deltaTime);
        }






    }
    public void dispose(){
        texture.dispose();
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, ancho, alto);
    }

    public void derecha(float delta){
        x+=velocidad*delta;


    }
    public void izquierda(float delta){
        x-=velocidad*delta;

    }



    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
