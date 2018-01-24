package com.mygdx.game.Entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.mygdx.game.Constantes.PIXELS_IN_METERS;

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

        if(this.getX()>14){
            this.eliminar=true;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texturamaullido,getX(),getY());
    }

}
