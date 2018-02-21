package com.mygdx.game.Ventanas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Constantes;
import com.mygdx.game.Entidades.Escenario1.Disparo;
import com.mygdx.game.Entidades.Escenario2.Boss;
import com.mygdx.game.Entidades.Escenario2.Disparo_Boss;
import com.mygdx.game.Entidades.Grisacius;
import com.mygdx.game.MainGame;
import com.mygdx.game.Tools.ScrollingBackground;

import java.util.ArrayList;

/**
 * Created by juana on 10/02/2018.
 */

public class GameScreenPrimerBoss implements Screen {

    public Texture background;
    private Boss jefe;
    public Game game;
    private Grisacius grisacius;
    protected ScrollingBackground fondo;
    public Music musicBoss;


    public GameScreenPrimerBoss(Game game){
        musicBoss= Gdx.audio.newMusic(Gdx.files.internal("music/loopbossmusic.mp3"));
        background=new Texture("imagenes/fondoscene2.png");
        fondo=new ScrollingBackground(background);

        grisacius=new Grisacius();
        jefe=new Boss(Constantes.ANCHO_PANTALLA-250);
        this.game=game;
    }


    @Override
    public void show() {

        musicBoss.play();
        musicBoss.setLooping(true);
    }

    @Override
    public void render(float delta) {

        grisacius.update(delta);
        jefe.update(delta,this.grisacius);




        //UPDATE DISPARO BOSS
        ArrayList<Disparo_Boss>disparosEliminar=new ArrayList<Disparo_Boss>();

        for(Disparo_Boss x:jefe.disparos){
            x.update(delta);
            if(x.remove){
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

        // COLISION jefe
        for (Disparo miau : grisacius.disparos) {

                if (miau.getColision().chocadoCon(jefe.getColision())) {

                    System.out.print("asd");
                    disparosDel.add(miau);
                       jefe.loseHealth();
                                   }
            }

            for(Disparo_Boss c:jefe.disparos){
            if(c.getColision().chocadoCon(grisacius.getColision())){
                this.dispose();
                game.setScreen(new GameOver(game));
            }
            }






        jefe.disparos.removeAll(disparosEliminar);
        grisacius.disparos.removeAll(disparosDel);

        Gdx.gl.glClearColor(0.1f, 0.4f, 0.6f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MainGame.batch.begin();

        fondo.updateAndRender(delta,MainGame.batch);


            jefe.render(MainGame.batch);
            grisacius.render(MainGame.batch);
            for(Disparo_Boss c:jefe.disparos){
                c.render(MainGame.batch);
            }
            for(Disparo x:grisacius.disparos){
                x.render(MainGame.batch);
            }
        if(jefe.health<=0){

            this.game.setScreen(new GameScreen(this.game));
            this.dispose();
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


        musicBoss.dispose();




    }
}
