package com.pokemongame.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Charizard {


    private Rectangle bounds;
    private Animation playerAnimation;
    private Texture texture;
    private Vector2 position;
    private Vector2 velocity;

    private boolean collided;


    public void setCollided(boolean collided) {
        this.collided = collided;
    }
    public Rectangle getBounds() {
        return bounds;
    }


    public Vector2 getPosition() {
        return position;
    }
    public TextureRegion getTexture() {
        return playerAnimation.getFrame();
    }

    public Charizard(int x, int y){
        position = new Vector2(x,y);
        collided = false;
        velocity = new Vector2(0,0);
        texture = new Texture("charizard_front_spritesheet.png");
        playerAnimation = new Animation(new TextureRegion(texture), 143, 5,29,3.5f);
        bounds = new Rectangle(position.x, position.y, texture.getWidth() / 3, texture.getHeight());
    }

    public void update(float dt){
        playerAnimation.update(dt);


        velocity.scl(1/dt);

        bounds.setPosition(position.x, position.y);
    }





    public void dispose() {

        texture.dispose();
    }
}
