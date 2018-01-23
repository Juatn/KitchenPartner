package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Entidades.Grisacius;
import com.mygdx.game.Entidades.Queso;
import com.mygdx.game.Entidades.RataNormal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juana on 23/01/2018.
 */

public class GameScreen extends BaseScreen {
    private Stage stage;
    private World world;
    private Grisacius grisacius;
    private List<RataNormal>listaRatas=new ArrayList<RataNormal>();


    public GameScreen(MainGame game) {
        super(game);
        stage=new Stage(new FitViewport(1920,1080));
        world=new World(new Vector2(0,0),true);
    }

    @Override
    public void show() {
       Texture texturaGrisacius=game.getManager().get("gatoo.png");
       Texture quesotexture=game.getManager().get("queso.png");
       Texture ratatexture=game.getManager().get("ratacartoon.png");

        grisacius=new Grisacius(world, texturaGrisacius,new Vector2(1,2));

        listaRatas.add(new RataNormal(world,ratatexture,new Vector2(6,3)));
        listaRatas.add(new RataNormal(world,ratatexture,new Vector2(6,2)));
        listaRatas.add(new RataNormal(world,ratatexture,new Vector2(6,1)));
        stage.addActor(grisacius);

        for(RataNormal c:listaRatas){
            stage.addActor(c);
        }

    }

    @Override
    public void hide() {
        grisacius.desacoplar();
        grisacius.remove();
        for(RataNormal c:listaRatas){
            c.desacoplar();
            c.remove();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        world.step(delta,6,2);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }
}
