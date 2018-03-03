package com.mygdx.grisacius.Ventanas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.grisacius.Entidades.Intro.Grisacius_Intro;
import com.mygdx.grisacius.MainGame;

import sun.applet.Main;

/**
 * Created by juana on 21/02/2018.
 */

public class IntroScreen implements Screen {

    public Game game;
    public Texture fondo;
    public Music introMusic;
    public Grisacius_Intro grisacius;
    private BitmapFont intro;

    public IntroScreen(Game game){
        this.game=game;
        grisacius=new Grisacius_Intro(200);
        introMusic= Gdx.audio.newMusic(Gdx.files.internal("music/introMusic.mp3"));
        intro=new BitmapFont(Gdx.files.internal("score.fnt"));
        fondo=new Texture("imagenes/intro.png");


    }

    @Override
    public void show() {
        introMusic.play();

    }

    @Override
    public void render(float delta) {
        grisacius.update(delta);

        if(! introMusic.isPlaying()){
            this.game.setScreen(new GameScreen(this.game));
        }


        Gdx.gl.glClearColor(0.1f, 0.4f, 0.6f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MainGame.batch.begin();

        MainGame.batch.draw(fondo,0,0);
        grisacius.render(MainGame.batch);
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

        fondo.getTextureData();


    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        fondo.dispose();
        introMusic.dispose();
        grisacius.dispose();

    }
}
