package com.mygdx.grisacius.Entidades.Escenario1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.grisacius.Tools.CompruebaColisiones;

import static com.mygdx.grisacius.Constantes.ANCHO_PANTALLA;
import static com.mygdx.grisacius.Constantes.VELOCIDAD_QUESO;

/**
 * Created by juana on 31/01/2018.
 */

public class Queso {
    //Atributos de clase
    private int alto_queso = 80;
    private int ancho_queso = 80;
    private Texture texture;
    public float x, y;
    public CompruebaColisiones colision;//
    public boolean remove = false;

    // Constructor

    public Queso(float y) {

        this.x = 1;
        this.y = y;
        this.colision = new CompruebaColisiones(y, x, ancho_queso, alto_queso);
        if (texture == null) {
            texture = new Texture("imagenes/cheese.png");
        }
    }

    public void update(float deltaTime, Rata rat) {

        if (rat.getX() < -rat.ANCHO_RATA)
            remove = true;
        rat.remove = true;
    }

    public void update(float deltaTime) {


        y -= VELOCIDAD_QUESO * deltaTime;
        if (y < 0) {
            y = ANCHO_PANTALLA;
        }


        colision.mover(x, y);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, ancho_queso, alto_queso);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public CompruebaColisiones getColision() {
        return colision;
    }


}
