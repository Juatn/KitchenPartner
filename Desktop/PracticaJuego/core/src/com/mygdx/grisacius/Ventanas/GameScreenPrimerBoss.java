package com.mygdx.grisacius.Ventanas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.grisacius.Constantes;
import com.mygdx.grisacius.Entidades.Escenario1.Disparo;
import com.mygdx.grisacius.Entidades.Escenario2.Boss;
import com.mygdx.grisacius.Entidades.Escenario2.Disparo_Boss;
import com.mygdx.grisacius.Entidades.Escenario2.Vida;
import com.mygdx.grisacius.Entidades.Grisacius;

import java.util.ArrayList;

import static com.mygdx.grisacius.Entidades.Escenario2.Boss.VIDA_BOSS;
import static com.mygdx.grisacius.MainGame.cam;

/**
 * Created by juana on 10/02/2018.
 */

public class GameScreenPrimerBoss implements Screen {

    public Texture background;
    private Boss jefe;
    public Game game;
    private Grisacius grisacius;
    public SpriteBatch batch;
    GlyphLayout vidaLayout;

    private ArrayList<Vida> vidas = new ArrayList<Vida>();
    public int posicionVida;
    protected BitmapFont vidafont;

    public Music musicBoss;


    public GameScreenPrimerBoss(Game game) {
        batch = new SpriteBatch();

        vidafont = new BitmapFont(Gdx.files.internal("score.fnt"));

        musicBoss = Gdx.audio.newMusic(Gdx.files.internal("music/loopbossmusic.mp3"));
        background = new Texture("imagenes/fondoscene2.png");


        grisacius = new Grisacius();
        jefe = new Boss(Constantes.ANCHO_PANTALLA - 250);
        posicionVida = 30;
        this.game = game;
        colocarVidas();
    }


    @Override
    public void show() {

        musicBoss.play();
        musicBoss.setLooping(true);


    }

    @Override
    public void render(float delta) {

        grisacius.update(delta);
        jefe.update(delta, this.grisacius);

        batch.setProjectionMatrix(cam.combined());


        //UPDATE DISPARO BOSS
        ArrayList<Disparo_Boss> disparosEliminar = new ArrayList<Disparo_Boss>();

        for (Disparo_Boss x : jefe.disparos) {
            x.update(delta);
            if (x.remove) {
                disparosEliminar.add(x);
                System.out.print("eliminado");
            }
        }

        //UPDATE DISPARO GRISACIUS
        ArrayList<Disparo> disparosDel = new ArrayList<Disparo>();
        for (Disparo miau : grisacius.disparos) {
            miau.update(delta);
            if (miau.remove)
                disparosDel.add(miau);

        }

        // COLISION  disparo jefe
        for (Disparo miau : grisacius.disparos) {

            if (miau.getColision().chocadoCon(jefe.getColision())) {

                System.out.print("asd");
                disparosDel.add(miau);


                jefe.loseHealth();
            }

        }

        // colision disparos jefe


        for (Disparo_Boss c : jefe.disparos)

        {
            if (c.getColision().chocadoCon(grisacius.getColision()) && !vidas.isEmpty()) {

                c.remove = true;
                vidas.remove(vidas.size() - 1);
                jefe.winHealth();
            }


        }
        if (vidas.isEmpty()) {
            this.game.setScreen(new GameOver(game));

        }


        jefe.disparos.removeAll(disparosEliminar);
        grisacius.disparos.removeAll(disparosDel);


        Gdx.gl.glClearColor(0.1f, 0.4f, 0.6f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.batch.begin();


        this.batch.draw(background, 0, 0);
        vidaLayout = new GlyphLayout(vidafont, "Vida:" + VIDA_BOSS);
        vidafont.draw(this.batch, vidaLayout, 50, 50);

        for (Vida c : vidas) {
            c.render(this.batch);
        }
        jefe.render(this.batch);
        grisacius.render(this.batch);
        for (Disparo_Boss c : jefe.disparos) {
            c.render(this.batch);
        }
        for (Disparo x : grisacius.disparos) {
            x.render(this.batch);
        }

        if (VIDA_BOSS <= 0) {

            VIDA_BOSS = 5000;
            Constantes.SCORE += 5;
            Constantes.dificultad();
            this.dispose();
            this.game.setScreen(new GameScreen(this.game));


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

        background.getTextureData();
        colocarVidas();

    }

    @Override
    public void hide() {
        musicBoss.dispose();
        jefe.dispose();


        background.dispose();

    }

    @Override
    public void dispose() {

        batch.dispose();
        musicBoss.dispose();
        jefe.disparos.clear();
        grisacius.disparos.clear();
        jefe.dispose();
        vidas.clear();
        vidafont.dispose();
        background.dispose();
        vidaLayout.reset();

    }

    public void colocarVidas() {
        for (int i = 0; i < 7; i++) {

            posicionVida += (Vida.ANCHO_VIDA + 8);
            vidas.add(new Vida(posicionVida));


        }


    }
}
