package com.mygdx.game.Entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.badlogic.gdx.scenes.scene2d.Actor;



/**
 * Created by juana on 23/01/2018.
 */

//

public class Maullido extends Actor {

    protected Texture texturamaullido;
    public static int VELOCIDAD=500;
    public boolean eliminar=false;

    public Maullido(Texture texturamaullido){
        this.texturamaullido=texturamaullido;

        setSize(texturamaullido.getWidth(),texturamaullido.getHeight());

    }

    @Override
    public void act(float delta) {
        setX(getX()+VELOCIDAD*delta);

        if(this.getY()>Gdx.graphics.getHeight()){
            this.eliminar=true;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texturamaullido,getX(),getY());
    }

}
