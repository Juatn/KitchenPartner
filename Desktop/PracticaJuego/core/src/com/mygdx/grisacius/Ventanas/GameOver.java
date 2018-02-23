package com.mygdx.grisacius.Ventanas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.grisacius.Entidades.GameOver.Rata_Over;
import com.mygdx.grisacius.MainGame;

/**
 * Created by juana on 21/02/2018.
 */

public class GameOver implements Screen {

    public Texture fondo;
    public Rata_Over rata;
    public Music gameoverMusic;
    public Game game;


    public GameOver(Game game){


           gameoverMusic= Gdx.audio.newMusic(Gdx.files.internal("music/gameover.mp3"));
           fondo=new Texture("imagenes/gameover.png");
           rata=new Rata_Over(50);

          this.game=game;
    }


    @Override
    public void show() {
        gameoverMusic.play();

    }

    @Override
    public void render(float delta) {


        rata.update(delta);
        Gdx.gl.glClearColor(0.1f, 0.4f, 0.6f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MainGame.batch.begin();
        MainGame.batch.draw(fondo,0,0);
        rata.render(MainGame.batch);
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
        gameoverMusic.dispose();
        fondo.dispose();
        MainGame.batch.dispose();
        game.dispose();
    }
}
