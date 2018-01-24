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

import static com.mygdx.game.Constantes.PIXELS_IN_METERS;

/**
 * Created by juana on 23/01/2018.
 */

//

public class Maullido extends Actor {

    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;


    public Maullido(World world, Texture texture, Vector2 position){
        this.world=world;
        this.texture=texture;

        //Definimos el cuerpo
        BodyDef def=new BodyDef();
        def.position.set(position);
        def.type= BodyDef.BodyType.DynamicBody;
        body=world.createBody(def);
        //Definimos la fixture
        CircleShape circulo =new CircleShape();
        circulo.setRadius(0.01f);
        fixture=body.createFixture(circulo, 1);
        fixture.setUserData("Maullido");
        circulo.dispose();

        setSize(PIXELS_IN_METERS,PIXELS_IN_METERS);

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
