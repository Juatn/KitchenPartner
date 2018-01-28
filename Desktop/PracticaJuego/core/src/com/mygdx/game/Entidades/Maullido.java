package com.mygdx.game.Entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.mygdx.game.Constantes.PIXELS_IN_METERS;
import static com.mygdx.game.Constantes.WIDTH;


/**
 * Created by juana on 23/01/2018.
 */

//

public class Maullido extends Actor {

    public Body body;
    protected int dmg = 50;
    protected float x, y;
    private Texture texture;
    private World world;
    private Fixture fixture;


    public Maullido(World world, Texture texture, Grisacius grisacius) {
        this.world = world;
        this.texture = texture;
        this.x = grisacius.body.getPosition().x + 0.8f;
        this.y = grisacius.body.getPosition().y;


        //Definimos el cuerpo.
        BodyDef def = new BodyDef();
        def.position.set(x, y);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
        //Definimos la fixture
        CircleShape circulo = new CircleShape();
        circulo.setRadius(0.3f);
        fixture = body.createFixture(circulo, 0.1f);
        fixture.setUserData("Maullido");
        circulo.dispose();


        setSize(10, 20);


    }

    @Override
    public void act(float delta) {
        super.act(delta);

        body.setLinearVelocity(10, 0);
        if (body.getPosition().x > WIDTH) {
            this.remove();
        }


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 0.5f) * PIXELS_IN_METERS,
                (body.getPosition().y - 0.5f) * PIXELS_IN_METERS);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    protected Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }


}