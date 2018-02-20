package com.mygdx.game.Entidades;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Tools.CompruebaColisiones;

/**
 * Created by juana on 20/02/2018.
 */

public interface MoldeRat {

    void update(float delta);
    void render(SpriteBatch batch);
    CompruebaColisiones getColision();
    float getX();
    float getY();



}
