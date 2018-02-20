package com.mygdx.game.Entidades.Escenario1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Tools.CompruebaColisiones;

import static com.mygdx.game.Constantes.ANCHO_PANTALLA;
import static com.mygdx.game.Constantes.DEFAULT_X;
import static com.mygdx.game.Constantes.VELOCIDAD_DISPARO;

/**
 * Created by juana on 29/01/2018.
 */

public class Disparo {


    private  Texture texture;
    public  int ANCHO_DISPARO = 40;
    public  int ALTO_DISPARO = 50;



    float x, y;
    CompruebaColisiones colision;
    public boolean remove = false;

    public Disparo(float y) {
        this.y = y;
        this.x = DEFAULT_X;
        this.colision = new CompruebaColisiones(x, y, ANCHO_DISPARO, ALTO_DISPARO);

        if (texture == null)
            texture = new Texture("imagenes/onda.png");
    }

    public void update(float deltaTime) {
        x += VELOCIDAD_DISPARO * deltaTime;
        if (x > ANCHO_PANTALLA)
            remove = true;


        colision.mover(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public CompruebaColisiones getColision() {
        return colision;
    }

}