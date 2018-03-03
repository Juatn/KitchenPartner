package com.mygdx.grisacius.Ventanas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.grisacius.Constantes;
import com.mygdx.grisacius.Entidades.Escenario1.Disparo;
import com.mygdx.grisacius.Entidades.Escenario2.Boss;
import com.mygdx.grisacius.Entidades.Escenario2.Disparo_Boss;
import com.mygdx.grisacius.Entidades.Escenario2.Vida;
import com.mygdx.grisacius.Entidades.Grisacius;
import com.mygdx.grisacius.MainGame;
import com.mygdx.grisacius.Tools.ScrollingBackground;

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
    private ArrayList<Vida>vidas=new ArrayList<Vida>();
    public int posicionVida;

    public Music musicBoss;


    public GameScreenPrimerBoss(Game game){

        musicBoss= Gdx.audio.newMusic(Gdx.files.internal("music/loopbossmusic.mp3"));
        background=new Texture("imagenes/fondoscene2.png");
        fondo=new ScrollingBackground(background);

        grisacius=new Grisacius();
        jefe=new Boss(Constantes.ANCHO_PANTALLA-250);
        posicionVida=30;
        this.game=game;
        for(int i=0;i<3;i++){

            posicionVida+=(Vida.ANCHO_VIDA+8);
            vidas.add(new Vida(posicionVida));


        }
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

        // COLISION  disparo jefe
        for (Disparo miau : grisacius.disparos) {

                if (miau.getColision().chocadoCon(jefe.getColision())) {

                    System.out.print("asd");
                    disparosDel.add(miau);

                    jefe.texture=new Texture("imagenes/bossdisparado.png");
                       jefe.loseHealth();
                                   }
            }

            // colision disparos jefe
        ArrayList<Vida>vidasEliminar=new ArrayList<Vida>();
            for(Disparo_Boss c:jefe.disparos)

                {
                    if (c.getColision().chocadoCon(grisacius.getColision())) {
                        c.remove=true;



                        if(!vidas.isEmpty()) {
                            vidasEliminar.add(vidas.get(0));
                        }

                    }



            }
        if(vidas.isEmpty()){
            this.game.setScreen(new GameOver(game));

        }





        jefe.disparos.removeAll(disparosEliminar);
        grisacius.disparos.removeAll(disparosDel);
        vidas.removeAll(vidasEliminar);

        Gdx.gl.glClearColor(0.1f, 0.4f, 0.6f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MainGame.batch.begin();

        fondo.updateAndRender(delta,MainGame.batch);

        for(Vida c:vidas){
            c.render(MainGame.batch);
        }
            jefe.render(MainGame.batch);
            grisacius.render(MainGame.batch);
            for(Disparo_Boss c:jefe.disparos){
                c.render(MainGame.batch);
            }
            for(Disparo x:grisacius.disparos){
                x.render(MainGame.batch);
            }

        if(jefe.health<=0){
                this.dispose();

            Constantes.dificultad();
            this.game.setScreen(new GameScreen(this.game));

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

        background.getTextureData();
        for(int i=0;i<3;i++){

            posicionVida+=(Vida.ANCHO_VIDA+8);
            vidas.add(new Vida(posicionVida));


        }

    }

    @Override
    public void hide() {
        musicBoss.dispose();
        jefe.dispose();


        background.dispose();

    }

    @Override
    public void dispose() {

        musicBoss.dispose();
        jefe.disparos.clear();
        grisacius.disparos.clear();
        jefe.dispose();
        vidas.clear();
        background.dispose();






    }
}
