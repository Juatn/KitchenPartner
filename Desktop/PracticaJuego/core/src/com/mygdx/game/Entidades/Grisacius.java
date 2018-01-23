package com.mygdx.game.Entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by juana on 23/01/2018.
 */

public class Grisacius  extends Actor{
    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;
    private boolean vivo;

    public Grisacius(World world, Texture texture,Vector2 position){
        this.world=world;
        this.texture=texture;

        //Definimos el cuerpo
        BodyDef def=new BodyDef();
        def.position.set(position);
        def.type= BodyDef.BodyType.DynamicBody;
        //Definimos la fixture
        CircleShape circulo =new CircleShape();
        circulo.setRadius(0.5f);
        fixture=body.createFixture(circulo,3);
        circulo.dispose();


    }
}
