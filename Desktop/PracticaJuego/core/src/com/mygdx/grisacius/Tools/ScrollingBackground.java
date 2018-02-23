package com.mygdx.grisacius.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.mygdx.grisacius.Constantes.ACELERACION;
import static com.mygdx.grisacius.Constantes.ACELERACION_OBJETIVO;
import static com.mygdx.grisacius.Constantes.ANCHO_PANTALLA;
import static com.mygdx.grisacius.Constantes.VELOCIDAD_DEFECTO;


/**
 * Created by juana on 29/01/2018.
 */

public class ScrollingBackground {


    Texture image;
    float y1, y2;
    int speed;
    int goalSpeed;
    float imageScale;
    boolean speedFixed;

    public ScrollingBackground(Texture img) {
        image =img ;

        y1 = 0;
        y2 = image.getHeight();
        speed = 0;
        goalSpeed = VELOCIDAD_DEFECTO;
        imageScale = ANCHO_PANTALLA / image.getWidth();
        speedFixed = true;
    }

    public void updateAndRender(float deltaTime, SpriteBatch batch) {

        if (speed < goalSpeed) {
            speed += ACELERACION_OBJETIVO * deltaTime;
            if (speed > goalSpeed)
                speed = goalSpeed;
        } else if (speed > goalSpeed) {
            speed -= ACELERACION_OBJETIVO * deltaTime;
            if (speed < goalSpeed)
                speed = goalSpeed;
        }

        if (!speedFixed)
            speed += ACELERACION * deltaTime;

        y1 -= speed * deltaTime;
        y2 -= speed * deltaTime;


        if (y1 + image.getHeight() * imageScale <= 0)
            y1 = y2 + image.getHeight() * imageScale;

        if (y2 + image.getHeight() * imageScale <= 0)
            y2 = y1 + image.getHeight() * imageScale;

        //Render
        batch.draw(image, 0, y1, ANCHO_PANTALLA, image.getHeight() * imageScale);
        batch.draw(image, 0, y2, ANCHO_PANTALLA, image.getHeight() * imageScale);
    }


}
