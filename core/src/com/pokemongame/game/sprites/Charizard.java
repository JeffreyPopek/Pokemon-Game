package com.pokemongame.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;


public class Charizard {

    private int health;
    private Rectangle bounds;
    private Texture texture;
    private Vector2 position;
    private Vector2 velocity;
    private Animation currentAnimation;

    private boolean collided;
    private boolean busy;


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
        return currentAnimation.getFrame();
    }

    public int getHealth() { return health; }

    public Charizard(int x, int y){
        health = 100;
        busy = false;
        position = new Vector2(x,y);
        collided = false;
        velocity = new Vector2(0,0);
        texture = new Texture("charizard_front_spritesheet.png");
        currentAnimation = new Animation(new TextureRegion(texture), 143, 5,29,3.5f);
        bounds = new Rectangle(position.x, position.y, texture.getWidth() / 3, texture.getHeight());
    }

    public void update(float dt){

        if (currentAnimation != null){
            currentAnimation.update(dt);
        }

        velocity.scl(1/dt);

        bounds.setPosition(position.x, position.y);
    }


    public void dispose() {
        texture.dispose();
    }


    public boolean isBusy() {
        return busy;
    }

    public void attack() {
        Texture attackTexture = new Texture("charizard_front_spritesheet.png");
        currentAnimation = new Animation(new TextureRegion(attackTexture), 143, 5,29,3.5f);
        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                busy = false;
            }
        }, 3.5f);
        this.busy = true;
    }
}
