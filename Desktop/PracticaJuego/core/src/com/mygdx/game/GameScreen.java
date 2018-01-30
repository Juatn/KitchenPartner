package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.Entidades.Disparo;
import com.mygdx.game.Entidades.Rata;

import java.util.ArrayList;
import java.util.Random;

import static com.badlogic.gdx.Input.Keys;
import static com.mygdx.game.Constantes.ALTO_PANTALLA;
import static com.mygdx.game.Constantes.ALTO_RATA;
import static com.mygdx.game.Constantes.GRISACIUS_ALTO;
import static com.mygdx.game.Constantes.GRISACIUS_ANCHO;
import static com.mygdx.game.Constantes.MAX_RATAS;
import static com.mygdx.game.Constantes.MIN_RATAS;
import static com.mygdx.game.Constantes.TIEMPO_DISPARO;
import static com.mygdx.game.Constantes.VELOCIDAD_GRISACIUS;
import static com.mygdx.game.Constantes.VELOCIDAD_RATA;

/**
 * Created by juana on 29/01/2018.
 */

class GameScreen implements Screen {

    //CONSTANTES

    public Game game;
    protected float grisaciusX, grisaciusY;
    protected float disparoTime;
    protected float statetime;
    float ratSpawnTimer;
    ArrayList<Disparo> disparos;
    ArrayList<Rata> ratas;
    protected Texture grisacius;

    protected Random random;
    public Texture background;
    public Music bgMusic;
    public Sound ratHit;
    protected BitmapFont scoreFont;
    protected int score;

    public GameScreen(Game game) {
        this.game = game;
        grisaciusY = 3;
        grisaciusX = 90;
        disparos = new ArrayList<Disparo>();
        ratas = new ArrayList<Rata>();
        random = new Random();
        ratSpawnTimer = random.nextFloat() * (MAX_RATAS - MIN_RATAS) + MIN_RATAS;
        disparoTime = 0;
        score = 0;
        scoreFont = new BitmapFont(Gdx.files.internal("score.fnt"));
        grisacius = new Texture("gato.png");
        background = new Texture("fondo.png");
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("spazzmatica.ogg"));
        ratHit = Gdx.audio.newSound(Gdx.files.internal("rataDisparada.wav"));
    }


    @Override
    public void show() {

        bgMusic.play();
        bgMusic.setLooping(true);

    }

    @Override
    public void render(float delta) {

        // codigo disparo
        disparoTime += delta;
        if ((isUP() || isDown()) && disparoTime >= TIEMPO_DISPARO) {
            disparoTime = 0;
            disparos.add(new Disparo(grisaciusY + 0.5f));

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
            if (rat.remove)
                ratasEliminar.add(rat);
        }
        //Update disparos
        ArrayList<Disparo> disparosEliminar = new ArrayList<Disparo>();
        for (Disparo miau : disparos) {
            miau.update(delta);
            if (miau.remove)
                disparosEliminar.add(miau);
        }

        // Codigo movimiento
        if (isUP()) {// ARRIBA
            grisaciusY += VELOCIDAD_GRISACIUS * Gdx.graphics.getDeltaTime();

            if (grisaciusY > Constantes.ALTO_PANTALLA)
                grisaciusY = Constantes.ALTO_PANTALLA - grisacius.getHeight();
        }
        if (isDown()) {//ABAJO
            grisaciusY -= VELOCIDAD_GRISACIUS * Gdx.graphics.getDeltaTime();

            if (grisaciusY < 0)
                grisaciusY = 0;
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
        disparos.removeAll(disparosEliminar);
        ratas.removeAll(ratasEliminar);
        statetime += delta;
        // aumentamos dificultad segun pase el tiempo
        dificultad();

        Gdx.gl.glClearColor(0.1f, 0.4f, 0.6f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MainGame.batch.begin();
        MainGame.batch.draw(background, 0, 0);

        MainGame.fondoAnimado.updateAndRender(delta, MainGame.batch);

        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "Score:" + score);
        scoreFont.draw(MainGame.batch, scoreLayout, 900, 690);

        if (bgMusic.isLooping())


            for (Disparo miau : disparos) {
                miau.render(MainGame.batch);
            }

        for (Rata rata : ratas) {
            rata.render(MainGame.batch);
        }
        MainGame.batch.draw(grisacius, grisaciusX, grisaciusY, GRISACIUS_ANCHO, GRISACIUS_ALTO);


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
        grisacius.dispose();


    }

    private boolean isUP() {
        return Gdx.input.isKeyPressed(Keys.UP) || (Gdx.input.isTouched() && MainGame.cam.getInputInGameWorld().y < ALTO_PANTALLA / 2);
    }

    private boolean isDown() {
        return Gdx.input.isKeyPressed(Keys.DOWN) || (Gdx.input.isTouched() && MainGame.cam.getInputInGameWorld().y >= ALTO_PANTALLA / 2);
    }


}
