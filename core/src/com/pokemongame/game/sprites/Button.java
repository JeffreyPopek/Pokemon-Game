package com.pokemongame.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Button {
    private Texture texture;
    private Vector2 position;
    public Button(int x, int y, String file){
        texture = new Texture(file);
        position = new Vector2(x, y);
    }
    public void dispose() {

        texture.dispose();
    }
    public Vector2 getPosition() {
        return position;
    }
    public Texture getTexture() {
        return texture;
    }
}
