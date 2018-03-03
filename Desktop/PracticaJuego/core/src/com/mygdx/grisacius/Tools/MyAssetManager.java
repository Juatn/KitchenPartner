package com.mygdx.grisacius.Tools;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by juana on 03/03/2018.
 */

public class MyAssetManager {

    public final AssetManager manager = new AssetManager();

    //Textures
    public final String vida = "imagenes/vida.png";
    public final String rataQuesoIz = "imagenes/rataQuesoIz.png";
    public final String rataQueso = "imagenes/rataQueso.png";
    public final String rata = "imagenes/rata.png";
    public final String onda = "imagenes/onda.png";
    public final String jefe1 = "imagenes/jefe1.png";
    public final String intro = "imagenes/intro.png";
    public final String gato = "imagenes/gato.png";
    public final String gameover = "imagenes/gameover.png";
    public final String fondoscene2 = "imagenes/fondoscene2.png";
    public final String fondo = "imagenes/fondo.png";
    public final String disparo_boss = "imagenes/disparo_boss.png";
    public final String disparo = "imagenes/disparo.png";
    public final String cheese = "imagenes/cheese.png";
    public final String bossrat = "imagenes/bossrat.png";
    public final String bossdisparado = "imagenes/bossdisparado.png";
    //Music
    public final String Mgameover = "music/gameover.mp3";
    public final String introMusic = "music/introMusic.mp3";
    public final String loopbgmusic = "music/loopbgmusic.mp3";
    public final String loopbossmusic = "music/loopbossmusic.mp3";
    public final String rataDisparada = "music/rataDisparada.mp3";
    //Sound
    public final String vozjefe2 = "music/voces/vozjefe2.mp3";
    public final String vozjefe3 = "music/voces/vozjefe3.mp3";


    public void loadTexture() {
        manager.load(vida, Texture.class);
        manager.load(rataQuesoIz, Texture.class);
        manager.load(rataQueso, Texture.class);
        manager.load(rata, Texture.class);
        manager.load(onda, Texture.class);
        manager.load(jefe1, Texture.class);
        manager.load(intro, Texture.class);
        manager.load(gato, Texture.class);
        manager.load(gameover, Texture.class);
        manager.load(fondoscene2, Texture.class);
        manager.load(fondo, Texture.class);
        manager.load(disparo_boss, Texture.class);
        manager.load(disparo, Texture.class);
        manager.load(cheese, Texture.class);
        manager.load(bossrat, Texture.class);
        manager.load(bossdisparado, Texture.class);
    }

    public void loadMusic() {
        manager.load(Mgameover, Music.class);
        manager.load(introMusic, Music.class);
        manager.load(loopbgmusic, Music.class);
        manager.load(loopbossmusic, Music.class);

    }

    public void loadSound() {
        manager.load(vozjefe2, Music.class);
        manager.load(vozjefe3, Music.class);
        manager.load(rataDisparada, Sound.class);
    }

    public void loadGameScreen(){

        //textures
        manager.load(rataQueso,Texture.class);
        manager.load(rata,Texture.class);
        manager.load(cheese,Texture.class);
        manager.load(gato,Texture.class);
        manager.load(rataQueso,Texture.class);
        //sounds
        manager.load(rataDisparada,Sound.class);
        //music
        manager.load(loopbgmusic, Sound.class);
    }


}
