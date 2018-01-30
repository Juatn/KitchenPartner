package com.mygdx.game.Tools;

/**
 * Created by juana on 29/01/2018.
 */

public class compruebaColisiones {

    float x, y;
    int ancho, alto;

    public compruebaColisiones(float x, float y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void mover(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean chocadoCon(compruebaColisiones b) {
        return x < b.x + b.ancho && y < b.y + b.alto && x + ancho > b.x && y + alto > b.y;
    }
}