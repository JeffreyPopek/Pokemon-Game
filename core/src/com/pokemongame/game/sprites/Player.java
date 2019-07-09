package com.pokemongame.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {


    private Rectangle bounds;
    private Animation birdAnimation;
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
        return birdAnimation.getFrame();
    }

    public Player(int x, int y){
        position = new Vector2(x,y);
        collided = false;
        velocity = new Vector2(0,0);
        texture = new Texture("mario.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(position.x, position.y, texture.getWidth() / 3, texture.getHeight());
    }

    public void update(float dt){
        birdAnimation.update(dt);


        velocity.scl(1/dt);

        bounds.setPosition(position.x, position.y);
    }





    public void dispose() {

        texture.dispose();
    }
}
