package com.mygdx.grisacius.Ventanas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.grisacius.Constantes;
import com.mygdx.grisacius.Entidades.GameOver.Rata_Over;

import static com.mygdx.grisacius.Constantes.SCORE;
import static com.mygdx.grisacius.MainGame.cam;
import static com.mygdx.grisacius.MainGame.dataBase;

/**
 * Created by juana on 21/02/2018.
 */

public class GameOver implements Screen {

    public Texture fondo;
    public Rata_Over rata;
    public Music gameoverMusic;
    public Game game;
    public SpriteBatch batch;


    public GameOver(Game game) {


        batch = new SpriteBatch();
        gameoverMusic = Gdx.audio.newMusic(Gdx.files.internal("music/gameover.mp3"));
        fondo = new Texture("imagenes/gameover.png");
        rata = new Rata_Over(50);
        dataBase.endCurrentGame(SCORE);
        gameoverMusic.play();

        this.game = game;
    }


    @Override
    public void show() {



    }

    @Override
    public void render(float delta) {

        batch.setProjectionMatrix(cam.combined());

        Gdx.gl.glClearColor(0.1f, 0.4f, 0.6f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.batch.begin();
        this.batch.draw(fondo, 0, 0);
        rata.update(delta);
        rata.render(this.batch);
        this.batch.end();

        if(!gameoverMusic.isPlaying()){
            this.dispose();
        }



    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

        fondo.getTextureData();
        rata = new Rata_Over(50);


    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        gameoverMusic.dispose();
        fondo.dispose();



    }
}
