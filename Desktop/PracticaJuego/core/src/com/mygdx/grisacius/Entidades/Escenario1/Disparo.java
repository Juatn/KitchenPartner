package com.mygdx.grisacius.Entidades.Escenario1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.grisacius.Tools.CompruebaColisiones;

import static com.mygdx.grisacius.Constantes.ANCHO_PANTALLA;
import static com.mygdx.grisacius.Constantes.DEFAULT_X;
import static com.mygdx.grisacius.Constantes.VELOCIDAD_DISPARO;

/**
 * Created by juana on 29/01/2018.
 */

public class Disparo {
   // Atributos de clase
    private Texture texture;
    private int ancho_disparo = 40;
    private int alto_disparo = 50;
    public float x, y;
    public CompruebaColisiones colision;
    public boolean remove = false;

    public Disparo(float y) {
        this.y = y;
        this.x = DEFAULT_X;
        this.colision = new CompruebaColisiones(x, y, ancho_disparo, alto_disparo);

        if (texture == null)
            texture = new Texture("imagenes/onda.png");
    }

    public void update(float deltaTime) {
        x += VELOCIDAD_DISPARO * deltaTime;
        if (x > ANCHO_PANTALLA)
            remove = true;


        colision.mover(x, y);
    }
    public int getAlto_disparo(){
        return this.alto_disparo;
    }
    public int getAncho_disparo(){
        return this.ancho_disparo;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public CompruebaColisiones getColision() {
        return colision;
    }

}