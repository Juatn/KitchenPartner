package com.mygdx.game.Entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Tools.compruebaColisiones;

import static com.mygdx.game.Constantes.ALTO_RATA;
import static com.mygdx.game.Constantes.ANCHO_PANTALLA;
import static com.mygdx.game.Constantes.ANCHO_RATA;
import static com.mygdx.game.Constantes.VELOCIDAD_RATA;


/**
 * Created by juana on 29/01/2018.
 */

//p

public class Rata {


    private static Texture texture;

    float x, y;

    compruebaColisiones colision;
    public boolean remove = false;

    public Rata(float y) {
        this.y = y;
        this.x = ANCHO_PANTALLA;
        this.colision = new compruebaColisiones(y, x, ALTO_RATA, ANCHO_RATA);

        if (texture == null)
            texture = new Texture("ratacartoon1.png");
    }

    public void update(float deltaTime) {
        x -= VELOCIDAD_RATA * deltaTime;
        if (x < -ANCHO_RATA)
            remove = true;

        colision.mover(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, ANCHO_RATA, ALTO_RATA);
    }

    public compruebaColisiones getColision() {
        return colision;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}