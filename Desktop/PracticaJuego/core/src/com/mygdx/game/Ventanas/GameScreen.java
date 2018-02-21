package com.mygdx.game.Ventanas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.Entidades.Escenario1.Disparo;
import com.mygdx.game.Entidades.Escenario1.Queso;
import com.mygdx.game.Entidades.Escenario1.Rata;
import com.mygdx.game.Entidades.Grisacius;
import com.mygdx.game.MainGame;
import com.mygdx.game.Tools.ScrollingBackground;

import java.util.ArrayList;
import java.util.Random;

import static com.mygdx.game.Constantes.ALTO_PANTALLA;

import static com.mygdx.game.Constantes.MAX_RATAS;
import static com.mygdx.game.Constantes.MIN_RATAS;

import static com.mygdx.game.Constantes.MIN_VELOCIDAD_RATA;
import static com.mygdx.game.Entidades.Escenario1.Rata.ALTO_RATA;
import static com.mygdx.game.Entidades.Grisacius.TIEMPO_DISPARO;

/**
 * Created by juana on 29/01/2018.
 */

public  class GameScreen implements Screen {


    public Game game;

    protected float disparoTime;
    protected float statetime;
    float ratSpawnTimer;
    ArrayList<Rata> ratas;
    public static ArrayList<Queso>quesos;
    protected ScrollingBackground fondo;
    protected Random random;
    public Music bgMusic;
        public Sound ratHit;
    protected BitmapFont scoreFont;
    protected BitmapFont quesosFont;
    protected int score;
    protected int posicionQuesos;

    protected Texture fondoTexture;
    protected Grisacius grisacius;
    public int contadorQuesos;


    public GameScreen(Game game) {
        this.game = game;
        posicionQuesos=30;



        grisacius=new Grisacius();
        contadorQuesos=10;
        fondoTexture=new Texture("imagenes/fondo.png");
        fondo=new ScrollingBackground(fondoTexture);
        quesosFont=new BitmapFont(Gdx.files.internal("score.fnt"));

        quesos=new ArrayList<Queso>();
        ratas = new ArrayList<Rata>();
       random = new Random();
        ratSpawnTimer = random.nextFloat() * (MAX_RATAS - MIN_RATAS) + MIN_RATAS;

        disparoTime = 0;
        score = 0;
        scoreFont = new BitmapFont(Gdx.files.internal("score.fnt"));


        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("music/loopbgmusic.mp3"));

        ratHit = Gdx.audio.newSound(Gdx.files.internal("music/rataDisparada.wav"));

    }


    @Override
    public void show() {

        bgMusic.play();
        bgMusic.setLooping(true);

        for(int i=0;i<10;i++){

            posicionQuesos+=(Queso.ALTO_QUESO+40);
            quesos.add(new Queso(posicionQuesos));


        }


    }

    @Override
    public void render(float delta) {


        grisacius.update(delta);

        // respawn ratas


        ratSpawnTimer -= delta;
        if (ratSpawnTimer <= 0) {
            ratSpawnTimer = random.nextFloat() * (MAX_RATAS - MIN_RATAS) + MIN_RATAS;
            ratas.add(new Rata(random.nextInt(ALTO_PANTALLA - ALTO_RATA)));
        }
        //Update ratas
        ArrayList<Rata> ratasEliminar = new ArrayList<Rata>();
        for (Rata rat : ratas) {
            rat.update(delta);
            if (rat.remove) {
                ratasEliminar.add(rat);
            }
        }

        //Update disparos
        ArrayList<Disparo> disparosEliminar = new ArrayList<Disparo>();
        for (Disparo miau : grisacius.disparos) {
            miau.update(delta);
            if (miau.remove)
                disparosEliminar.add(miau);
        }



        //COLISIONES
        for (Disparo miau : grisacius.disparos) {
            for (Rata rata : ratas) {
                if (miau.getColision().chocadoCon(rata.getColision())) {
                    ratHit.play(0.2f);
                    disparosEliminar.add(miau);
                    ratasEliminar.add(rata);
                    score += 5;


                }
            }
        }

        ArrayList<Queso>quesosEliminar=new ArrayList<Queso>();
        for(Queso queso:quesos){
            queso.update(delta);
            for(Rata rata:ratas){
                if(rata.getColision().chocadoCon(queso.getColision())){
                   queso.setTexture(new Texture("imagenes/rataQueso.png"));
                   ratasEliminar.add(rata);
                   --contadorQuesos;

                }
            }
        }





        quesos.removeAll(quesosEliminar);
        grisacius.disparos.removeAll(disparosEliminar);
        ratas.removeAll(ratasEliminar);

        statetime += delta;
        // aumentamos dificultad segun pase el tiempo.
        dificultad();

        Gdx.gl.glClearColor(0.1f, 0.4f, 0.6f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MainGame.batch.begin();


        fondo.updateAndRender(delta, MainGame.batch);

        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "Score:" + score);
        GlyphLayout quesosLayout=new GlyphLayout(quesosFont,"Quesos:"+contadorQuesos);
        scoreFont.draw(MainGame.batch, scoreLayout, 900, 690);
        quesosFont.draw(MainGame.batch,quesosLayout,100,690);

        if (bgMusic.isLooping()) {

            for (Queso queso : quesos) {
                queso.render(MainGame.batch);
            }


            for (Disparo miau : grisacius.disparos) {
                miau.render(MainGame.batch);
            }

            for (Rata rata : ratas) {
                rata.render(MainGame.batch);
            }

            grisacius.render(MainGame.batch);
        }
        if(contadorQuesos<=0){

            this.dispose();
            this.game.setScreen(new GameOver(this.game));

        }
        if(score>200){
            this.dispose();
            this.game.setScreen(new GameScreenPrimerBoss(this.game));
        }

        MainGame.batch.end();


    }

    public void dificultad() {
        MIN_RATAS -= 0.00001f;
        MIN_VELOCIDAD_RATA+=0.06f;

        TIEMPO_DISPARO -= 0.000001f;
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
        bgMusic.stop();



    }




}
