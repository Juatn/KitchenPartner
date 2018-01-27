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
import com.mygdx.game.Tools.Colision;

import java.util.ArrayList;

import static com.mygdx.game.Constantes.PIXELS_IN_METERS;
import static com.mygdx.game.GameScreen.HEIGHT;
import static com.mygdx.game.GameScreen.WIDTH;


/**
 * Created by juana on 23/01/2018.
 */

//

public class Maullido extends Actor {

    private Texture texture;
    private World world;
    public Body body;
    private Fixture fixture;
    public Colision colision;
    private boolean remove=false;
    protected int dmg=50;
    protected float x,y;


    public Maullido(World world, Texture texture,Grisacius grisacius){
        this.world=world;
        this.texture=texture;
        this.x=x+2.5f;
        this.y=grisacius.body.getPosition().y;


        //Definimos el cuerpo.
        BodyDef def=new BodyDef();
        def.position.set(x,y);
        def.type= BodyDef.BodyType.DynamicBody;
        body=world.createBody(def);
        //Definimos la fixture
        CircleShape circulo =new CircleShape();
        circulo.setRadius(0.3f);
        fixture=body.createFixture(circulo, 0.1f);
        fixture.setUserData("Maullido");
        circulo.dispose();


        setSize(10,20);
        this.colision=new Colision(x,y,WIDTH,HEIGHT);

    }

    @Override
    public void act(float delta) {

        body.setLinearVelocity(10,0);
        if (body.getPosition().x > WIDTH) {
            System.out.print("Maullido eliminado"); // Prueba para comprobar que se elimina la textura
            this.remove();
        }

        colision.move(x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x-0.5f)*PIXELS_IN_METERS,
                (body.getPosition().y-0.5f)*PIXELS_IN_METERS);
        batch.draw(texture,getX(),getY(),getWidth(),getHeight());
    }


    public Colision getColision(){
        return this.colision;
    }

}