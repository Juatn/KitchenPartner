package com.mygdx.grisacius.Ventanas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.grisacius.Entidades.Escenario1.Disparo;
import com.mygdx.grisacius.Entidades.Escenario1.Queso;
import com.mygdx.grisacius.Entidades.Escenario1.Rata;
import com.mygdx.grisacius.Entidades.Grisacius;
import com.mygdx.grisacius.MainGame;
import com.mygdx.grisacius.Tools.ScrollingBackground;

import java.util.ArrayList;
import java.util.Random;

import static com.mygdx.grisacius.Constantes.ALTO_PANTALLA;
import static com.mygdx.grisacius.Constantes.MAX_RATAS;
import static com.mygdx.grisacius.Constantes.MIN_RATAS;
import static com.mygdx.grisacius.Constantes.SCORE;
import static com.mygdx.grisacius.Entidades.Escenario1.Rata.ALTO_RATA;
import static com.mygdx.grisacius.MainGame.cam;

/**
 * Created by juana on 29/01/2018.
 */

public class GameScreen implements Screen {


    public static Game game;
    public SpriteBatch batch;


    protected float disparoTime;
    protected float statetime;
    float ratSpawnTimer;
    ArrayList<Rata> ratas;
    public static ArrayList<Queso> quesos;
    protected ScrollingBackground fondo;
    protected Random random;
    public Music bgMusic;
    public Sound ratHit;
    protected BitmapFont scoreFont;
    protected BitmapFont quesosFont;


    protected int posicionQuesos;

    protected Texture fondoTexture;
    protected Grisacius grisacius;
    public int contadorQuesos;


    public GameScreen(Game game) {
        this.game = game;


        posicionQuesos = 30;
        batch = new SpriteBatch();


        grisacius = new Grisacius();
        contadorQuesos = 10;
        fondoTexture = new Texture("imagenes/fondo.png");
        fondo = new ScrollingBackground(fondoTexture);
        quesosFont = new BitmapFont(Gdx.files.internal("score.fnt"));

        quesos = new ArrayList<Queso>();
        ratas = new ArrayList<Rata>();
        random = new Random();
        ratSpawnTimer = random.nextFloat() * (MAX_RATAS - MIN_RATAS) + MIN_RATAS;

        disparoTime = 0;

        scoreFont = new BitmapFont(Gdx.files.internal("score.fnt"));


        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("music/loopbgmusic.mp3"));

        ratHit = Gdx.audio.newSound(Gdx.files.internal("music/rataDisparada.wav"));
        bgMusic.play();
        bgMusic.setLooping(true);

    }


    @Override
    public void show() {


        for (int i = 0; i < 10; i++) {

            posicionQuesos += (Queso.ALTO_QUESO + 40);
            quesos.add(new Queso(posicionQuesos));


        }


    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(cam.combined());

        if (!bgMusic.isPlaying()) {
            bgMusic.play();
        }


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
                    SCORE += 5;
                    MainGame.dataBase.saveCurrentGame(SCORE);


                }
            }
        }

        ArrayList<Queso> quesosEliminar = new ArrayList<Queso>();
        for (Queso queso : quesos) {
            queso.update(delta);
            for (Rata rata : ratas) {
                if (rata.getColision().chocadoCon(queso.getColision())) {
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
        if (SCORE == 100 || SCORE == 500) {

            this.dispose();


            this.game.setScreen(new GameScreenPrimerBoss(this.game));

        }
        if (contadorQuesos <= 0) {

            this.dispose();
            this.game.setScreen(new GameOver(this.game));

        }

        Gdx.gl.glClearColor(0.1f, 0.4f, 0.6f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.batch.begin();


        fondo.updateAndRender(delta, this.batch);

        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "Score:" + SCORE);
        GlyphLayout quesosLayout = new GlyphLayout(quesosFont, "Quesos:" + contadorQuesos);
        scoreFont.draw(this.batch, scoreLayout, 900, 690);
        quesosFont.draw(this.batch, quesosLayout, 100, 690);

        if (bgMusic.isLooping()) {

            for (Queso queso : quesos) {
                queso.render(this.batch);
            }


            for (Disparo miau : grisacius.disparos) {
                miau.render(this.batch);
            }

            for (Rata rata : ratas) {
                rata.render(this.batch);
            }

            grisacius.render(this.batch);
        }


        this.batch.end();


    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {


        for (int i = 0; i < 10; i++) {


            posicionQuesos += (Queso.ALTO_QUESO + 40);
            quesos.add(new Queso(posicionQuesos));


        }


    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        bgMusic.dispose();
        fondoTexture.dispose();
        quesos.clear();
        ratas.clear();
        scoreFont.dispose();
        quesosFont.dispose();

    }


}
