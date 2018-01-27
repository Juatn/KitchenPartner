package com.mygdx.game.Tools;

import com.badlogic.gdx.physics.box2d.Contact;

/**
 * Created by juana on 27/01/2018.
 */

public class Colision {
    float x, y;
    float width, height;

    public Colision(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean collidesWith(Colision rect) {

        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
    }


}
