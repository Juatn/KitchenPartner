package com.mygdx.game.Entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Tools.Colision;

import static com.mygdx.game.Constantes.PIXELS_IN_METERS;
import static com.mygdx.game.GameScreen.*;

/**
 * Created by juana on 23/01/2018.
 */

public class RataNormal extends Actor {

    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;
    public Colision colision;
    private boolean viva;
    protected int vida=100;
    protected float x,y;

    public RataNormal(World world, Texture texture,float y){
        this.world=world;
        this.texture=texture;
        this.x= WIDTH;
        this.y=y;


        //Definimos el cuerpo.
        BodyDef def=new BodyDef();
        def.position.set(x,y);
        def.type= BodyDef.BodyType.DynamicBody;
        body=world.createBody(def);
        //Definimos la fixture
        CircleShape circulo =new CircleShape();
        circulo.setRadius(0.3f);
        fixture=body.createFixture(circulo, 1);
        fixture.setUserData("Rata");
        circulo.dispose();


        setSize(45,50);
        this.colision=new Colision(x,y,WIDTH,HEIGHT);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x-0.5f)*PIXELS_IN_METERS,
                (body.getPosition().y-0.5f)*PIXELS_IN_METERS);
        batch.draw(texture,getX(),getY(),getWidth(),getHeight());
    }

    public void desacoplar(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
