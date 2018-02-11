package com.mygdx.game.Entidades.Escenario1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Constantes;
import com.mygdx.game.Tools.CompruebaColisiones;

import static com.mygdx.game.Constantes.VELOCIDAD_QUESO;

/**
 * Created by juana on 31/01/2018.
 */

public class Game_Over {
    private static Texture texture;
    public float x,y;

    public boolean remove=false;



    public Game_Over(){

        this.x=0;
        this.y=0;

        if (texture == null) {
            texture = new Texture("gameover1.png");
        }
    }


    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, Constantes.ANCHO_PANTALLA, Constantes.ALTO_PANTALLA);
    }



}
