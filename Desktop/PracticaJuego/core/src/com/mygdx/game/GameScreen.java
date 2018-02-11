package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.Entidades.Escenario1.Disparo;
import com.mygdx.game.Entidades.Escenario1.Game_Over;
import com.mygdx.game.Entidades.Escenario1.Queso;
import com.mygdx.game.Entidades.Escenario1.Rata;
import com.mygdx.game.Entidades.Grisacius;
import com.mygdx.game.Tools.ScrollingBackground;

import java.util.ArrayList;
import java.util.Random;
import static com.badlogic.gdx.Input.Keys;
import static com.mygdx.game.Constantes.ALTO_PANTALLA;

import static com.mygdx.game.Constantes.MAX_RATAS;
import static com.mygdx.game.Constantes.MIN_RATAS;

import static com.mygdx.game.Constantes.VELOCIDAD_RATA;
import static com.mygdx.game.Entidades.Escenario1.Rata.ALTO_RATA;
import static com.mygdx.game.Entidades.Grisacius.GRISACIUS_ALTO;
import static com.mygdx.game.Entidades.Grisacius.GRISACIUS_ANCHO;
import static com.mygdx.game.Entidades.Grisacius.TIEMPO_DISPARO;

/**
 * Created by juana on 29/01/2018.
 */

class GameScreen implements Screen {


    public Game game;

    protected float disparoTime;
    protected float statetime;
    float ratSpawnTimer;
    ArrayList<Disparo> disparos;
    ArrayList<Rata> ratas;
    public static ArrayList<Queso>quesos;

    protected ScrollingBackground fondo;

    protected Random random;
    public Music bgMusic;
    public Music gameoverMusic;
    public Sound ratHit;
    protected BitmapFont scoreFont;
    protected BitmapFont quesosFont;
    protected int score;
    protected int posicionQuesos;
    protected Game_Over gameover;
    protected Texture fondoTexture;
    protected Grisacius grisacius;


    public GameScreen(Game game) {
        this.game = game;
               posicionQuesos=30;



        grisacius=new Grisacius();

        fondoTexture=new Texture("fondo.png");
        fondo=new ScrollingBackground(fondoTexture);
        quesosFont=new BitmapFont(Gdx.files.internal("score.fnt"));
         gameover=new Game_Over();
        quesos=new ArrayList<Queso>();
        disparos = new ArrayList<Disparo>();
        ratas = new ArrayList<Rata>();
        random = new Random();
        ratSpawnTimer = random.nextFloat() * (MAX_RATAS - MIN_RATAS) + MIN_RATAS;
        disparoTime = 0;
        score = 0;
        scoreFont = new BitmapFont(Gdx.files.internal("score.fnt"));


        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("spazzmatica.ogg"));
        gameoverMusic=Gdx.audio.newMusic(Gdx.files.internal("gameover.mp3"));
        ratHit = Gdx.audio.newSound(Gdx.files.internal("rataDisparada.wav"));
    }


    @Override
    public void show() {

        bgMusic.play();
        bgMusic.setLooping(true);

        for(int i=0;i<10;i++){
            quesos.add(new Queso(posicionQuesos));
            posicionQuesos+=(Queso.ALTO_QUESO+40);

        }


    }

    @Override
    public void render(float delta) {


        grisacius.update(delta);

        // codigo disparo
        disparoTime += delta;
        if ((grisacius.isUP() || grisacius.isDown()) && disparoTime >= TIEMPO_DISPARO) {
            disparoTime = 0;
            disparos.add(new Disparo(grisacius.getY() + 0.5f));

        }


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
        for (Disparo miau : disparos) {
            miau.update(delta);
            if (miau.remove)
                disparosEliminar.add(miau);
        }



        //COLISIONES
        for (Disparo miau : disparos) {
            for (Rata rata : ratas) {
                if (miau.getColision().chocadoCon(rata.getColision())) {
                    ratHit.play();
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
                   quesosEliminar.add(queso);
                   ratasEliminar.add(rata);

                }
            }
        }





        quesos.removeAll(quesosEliminar);
        disparos.removeAll(disparosEliminar);
        ratas.removeAll(ratasEliminar);

        statetime += delta;
        // aumentamos dificultad segun pase el tiempo.
        dificultad();

        Gdx.gl.glClearColor(0.1f, 0.4f, 0.6f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MainGame.batch.begin();


        fondo.updateAndRender(delta, MainGame.batch);

        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "Score:" + score);
        GlyphLayout quesosLayout=new GlyphLayout(quesosFont,"Quesos:"+quesos.size());
        scoreFont.draw(MainGame.batch, scoreLayout, 900, 690);
        quesosFont.draw(MainGame.batch,quesosLayout,100,690);

        if (bgMusic.isLooping())

            for(Queso queso:quesos){
            queso.render(MainGame.batch);
            }


            for (Disparo miau : disparos) {
                miau.render(MainGame.batch);
            }

        for (Rata rata : ratas) {
            rata.render(MainGame.batch);
        }
        grisacius.render(MainGame.batch);

        if(quesos.isEmpty()){

            gameover.render(MainGame.batch);
            bgMusic.stop();
            gameoverMusic.play();
        }

        MainGame.batch.end();


    }

    public void dificultad() {
        MIN_RATAS -= 0.000001f;
        VELOCIDAD_RATA += 0.5f;
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

        gameoverMusic.stop();


    }




}
