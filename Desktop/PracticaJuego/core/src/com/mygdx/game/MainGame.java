package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class MainGame extends Game {
    private AssetManager manager;

    public AssetManager getManager() {
        return manager;
    }

    @Override
    public void create() {
        manager = new AssetManager();
        manager.load("gatoo1.png", Texture.class);
        manager.load("queso.png", Texture.class);
        manager.load("ratacartoon1.png", Texture.class);
        manager.load("maullido.png", Texture.class);
        manager.load("spazzmatica.ogg", Music.class);

        // Se va a cargar de forma sincrona (Syncronized)
        manager.finishLoading();

        setScreen(new GameScreen(this));
    }
}
