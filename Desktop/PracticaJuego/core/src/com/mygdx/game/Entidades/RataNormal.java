package com.mygdx.game.Entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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

public class RataNormal extends Actor {

    public Body body;
    public Fixture fixture;
    protected int vida = 100;
    protected float x, y;
    private Texture texture;
    private World world;
    private boolean viva = true;


    public RataNormal(World world, Texture texture, float y) {
        this.world = world;
        this.texture = texture;
        this.x = WIDTH;
        this.y = y;


        //Definimos el cuerpo.
        BodyDef def = new BodyDef();
        def.position.set(x, y);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
        //Definimos la fixture
        CircleShape circulo = new CircleShape();
        circulo.setRadius(0.5f);
        fixture = body.createFixture(circulo, 1);
        fixture.setUserData("Rata");
        circulo.dispose();


        setSize(55, 60);


    }

    @Override
    public void act(float delta) {

        body.setLinearVelocity(-2f, 0);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 0.5f) * PIXELS_IN_METERS,
                (body.getPosition().y - 0.5f) * PIXELS_IN_METERS);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void desacoplar() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }


    public boolean isViva() {
        return viva;
    }

    public void setViva(boolean viva) {
        this.viva = viva;
    }

}
