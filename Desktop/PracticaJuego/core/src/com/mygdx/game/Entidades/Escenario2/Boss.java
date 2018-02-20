package com.mygdx.game.Entidades.Escenario2;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Constantes;
import com.mygdx.game.Entidades.Grisacius;
import com.mygdx.game.Entidades.MoldeBoss;
import com.mygdx.game.Tools.CompruebaColisiones;

import java.util.ArrayList;
import java.util.Random;

import static com.mygdx.game.Constantes.ALTO_PANTALLA;


/**
 * Created by juana on 10/02/2018.
 */

public class Boss  implements MoldeBoss{
    public static int ANCHO_BOSS = 200;
    public static int ALTO_BOSS = 400;
    public float x,y;
    public  Texture texture;
    public CompruebaColisiones colision;
    public boolean remove= false;
    public  int health=5000;
    public Random posibilidad;
    public static int VELOCIDAD_BOSS=500;
    public ArrayList<Disparo_Boss> disparos=new ArrayList<Disparo_Boss>();


    public Boss(float x ){
        this.y = Constantes.ALTO_PANTALLA/2;
        this.x = x;
        this.colision = new CompruebaColisiones(y, x, ALTO_BOSS, ANCHO_BOSS);
        this.posibilidad=new Random();

        if (texture == null) {
            texture = new Texture("boss1.png");
        }
    }

    public void update(float deltaTime, Grisacius grisacius){

        int aleatorio = posibilidad.nextInt(50);

        if(aleatorio==25) {

            disparos.add(new Disparo_Boss(this));


        }


        if(posibilidad.nextBoolean()){
            sube(aleatorio,deltaTime);
            colision.mover(x, y);
        }
        else{
            baja(aleatorio,deltaTime);
            colision.mover(x, y);
        }
        if(this.y>ALTO_PANTALLA){
           this.y=(ALTO_PANTALLA-ALTO_BOSS);
        }

        if(this.y<0){
           this.y=0;
        }

        posibilidad.setSeed(System.currentTimeMillis());



    }

    public void sube(float speed,float deltatime){
        y += VELOCIDAD_BOSS * deltatime;
    }
    public void baja(float speed, float deltatime){
        y-=VELOCIDAD_BOSS*deltatime;
    }




    @Override
    public void loseHealth() {
        health=health-1000;

    }

    @Override
    public void winHealth() {
        health=health+500;
    }
    public void render(SpriteBatch batch) {

        batch.draw(texture, x, y, ANCHO_BOSS, ALTO_BOSS);
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
}
