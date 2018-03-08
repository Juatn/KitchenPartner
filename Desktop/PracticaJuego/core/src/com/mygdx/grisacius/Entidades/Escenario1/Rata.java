package com.mygdx.grisacius.Entidades.Escenario1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.grisacius.Entidades.MoldeRat;
import com.mygdx.grisacius.Tools.CompruebaColisiones;

import static com.mygdx.grisacius.Constantes.ANCHO_PANTALLA;
import static com.mygdx.grisacius.Constantes.MAX_VELOCIDAD_RATA;
import static com.mygdx.grisacius.Constantes.MIN_VELOCIDAD_RATA;
import static com.mygdx.grisacius.Constantes.getInt;


/**
 * Created by juana on 29/01/2018.
 */

//p

public class Rata implements MoldeRat {


    private static Texture texture;
    private static int ANCHO_RATA = 100;
    public static int ALTO_RATA = 100;
    public float x, y;
    public int velocidad;


    CompruebaColisiones colision;
    public boolean remove = false;
    public boolean haComido = false;

    public Rata(float y) {
        this.y = y;
        this.x = ANCHO_PANTALLA;
        velocidad = getInt(MIN_VELOCIDAD_RATA, MAX_VELOCIDAD_RATA);
        this.colision = new CompruebaColisiones(y, x, ALTO_RATA, ANCHO_RATA);

        if (texture == null)
            texture = new Texture("imagenes/rata.png");
    }

    public void update(float deltaTime) {
        x -= velocidad * deltaTime;
        if (x < -ANCHO_RATA) {
            remove = true;
        }


        colision.mover(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, ANCHO_RATA, ALTO_RATA);
    }

    public CompruebaColisiones getColision() {
        return colision;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


}