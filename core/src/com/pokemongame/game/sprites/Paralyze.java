package com.pokemongame.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Paralyze {
    Vector2 position;
    Texture texture;


    public Paralyze(float x, float y){
        position = new Vector2(x, y);
        texture = new Texture("paralyze_status.png");

    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return position;
    }
}
