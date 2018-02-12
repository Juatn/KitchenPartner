package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entidades.Escenario2.Boss;
import com.mygdx.game.Entidades.Escenario2.Disparo_Boss;
import com.mygdx.game.Entidades.Grisacius;
import com.mygdx.game.Tools.ScrollingBackground;

import sun.applet.Main;

/**
 * Created by juana on 10/02/2018.
 */

public class GameScreen2 implements Screen {

    public Texture background;
    private Boss jefe;
    public Game game;
    private Grisacius grisacius;
    protected ScrollingBackground fondo;
    public Music musicBoss;


    public GameScreen2(Game game){
        musicBoss= Gdx.audio.newMusic(Gdx.files.internal("bossmusic.mp3"));
        background=new Texture("fondo.png");
        fondo=new ScrollingBackground(background);
        this.game=game;
        grisacius=new Grisacius();
        jefe=new Boss(Constantes.ANCHO_PANTALLA-250);

    }


    @Override
    public void show() {

        musicBoss.play();
    }

    @Override
    public void render(float delta) {

        grisacius.update(delta);
        jefe.update(delta,this.grisacius);
        Gdx.gl.glClearColor(0.1f, 0.4f, 0.6f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MainGame.batch.begin();
        fondo.updateAndRender(delta,MainGame.batch);
        jefe.render(MainGame.batch);
        grisacius.render(MainGame.batch);
        for(Disparo_Boss c: jefe.disparos){
            c.update(delta);
            c.render(MainGame.batch);
        }

        MainGame.batch.end();

    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        MainGame.batch.dispose();


    }
}
