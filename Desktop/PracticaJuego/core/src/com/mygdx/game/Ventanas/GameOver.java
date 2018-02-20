package com.mygdx.game.Ventanas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MainGame;

/**
 * Created by juana on 21/02/2018.
 */

public class GameOver implements Screen {

    public Texture fondo;
    public Music gameoverMusic;
    public Game game;

    public GameOver(Game game){


           gameoverMusic= Gdx.audio.newMusic(Gdx.files.internal("music/gameover.mp3"));
           fondo=new Texture("imagenes/gameover.png");
          this.game=game;
    }


    @Override
    public void show() {
        gameoverMusic.play();

    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0.1f, 0.4f, 0.6f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MainGame.batch.begin();
        MainGame.batch.draw(fondo,0,0);

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
    }
}
