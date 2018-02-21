package com.mygdx.game.Entidades.GameOver;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import static com.mygdx.game.Constantes.MAX_TERMINAR;

import static com.mygdx.game.Constantes.MIN_EMPEZAR;


/**
 * Created by juana on 21/02/2018.
 */
public class Rata_Over {


    private static Texture texture;
    public  int ANCHO_RATA = 280;
    public  int ALTO_RATA = 280;
    public boolean derecha;

    public float x, y;
    public int velocidad;




    public Rata_Over(float y) {
        this.y = y;
        this.x = 200;
        velocidad=100;


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

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, ANCHO_RATA, ALTO_RATA);
    }

    public void derecha(float delta){
        x+=velocidad*delta;
        texture=new Texture("imagenes/rataQueso.png");

    }
    public void izquierda(float delta){
        x-=velocidad*delta;
        texture=new Texture("imagenes/rataQuesoIz.png");
    }



    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}


