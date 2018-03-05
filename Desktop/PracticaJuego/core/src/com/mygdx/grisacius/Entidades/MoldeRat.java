package com.mygdx.grisacius.Entidades;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.grisacius.Tools.CompruebaColisiones;

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
