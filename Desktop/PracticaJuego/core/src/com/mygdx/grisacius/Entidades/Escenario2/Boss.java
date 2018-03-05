package com.mygdx.grisacius.Entidades.Escenario2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.grisacius.Constantes;
import com.mygdx.grisacius.Entidades.Grisacius;
import com.mygdx.grisacius.Entidades.MoldeBoss;
import com.mygdx.grisacius.Tools.CompruebaColisiones;

import java.util.ArrayList;
import java.util.Random;

import static com.mygdx.grisacius.Constantes.ALTO_PANTALLA;


/**
 * Created by juana on 10/02/2018.
 */

public class Boss  implements MoldeBoss{
    public static int ANCHO_BOSS = 200;
    public static int ALTO_BOSS = 400;
    public float x,y;
    public  Texture texture;
    public Sprite spriteBoss;
    public CompruebaColisiones colision;
    public boolean remove= false;
    public static int VIDA_BOSS=5000;
    public Random posibilidad;
    public static int VELOCIDAD_BOSS=500;
    public ArrayList<Disparo_Boss> disparos=new ArrayList<Disparo_Boss>();




    public Boss(float x ){
        this.y = Constantes.ALTO_PANTALLA/2;
        this.x = x;
        this.colision = new CompruebaColisiones(y, x, ALTO_BOSS, ANCHO_BOSS);
        this.posibilidad=new Random();
        if (texture==null) {
            texture = new Texture("imagenes/bossrat.png");
        }
        spriteBoss=new Sprite(texture);




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
        VIDA_BOSS=VIDA_BOSS-100;
        //System.out.print("pierde vida");

    }

    @Override
    public void winHealth() {
        VIDA_BOSS=VIDA_BOSS+500;
    }
    public void render(SpriteBatch batch) {

        batch.draw(spriteBoss, x, y, ANCHO_BOSS, ALTO_BOSS);
    }

    public void dispose(){
        this.texture.dispose();

        this.disparos.clear();
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
