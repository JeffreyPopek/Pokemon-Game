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
    private Vector2 positionBeforeAnimation;
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

    public int getHealth() {
        return health;
    }

    public Charizard(int x, int y) {
        health = 2000;
        busy = false;
        position = new Vector2(x, y);
        collided = false;
        velocity = new Vector2(0, 0);
        texture = new Texture("charizard_front_spritesheet.png");
        currentAnimation = new Animation(new TextureRegion(texture), 143, 5, 29, 3.5f);
        bounds = new Rectangle(position.x, position.y, texture.getWidth() / 3, texture.getHeight());
    }

    public void update(float dt) {

        if (currentAnimation != null) {
            currentAnimation.update(dt);
        }

        velocity.scl(1 / dt);

        bounds.setPosition(position.x, position.y);
    }


    public void dispose() {
        texture.dispose();
    }


    public boolean isBusy() {
        return busy;
    }

    /*public void attack() {

        Texture attackTexture = new Texture("charizard-attack.png");
        currentAnimation = new Animation(new TextureRegion(attackTexture), 18, 5,4,1.8f);
        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                busy = false;
            }
        }, 3.5f);
        this.busy = true;
    }
} */

    public void takeDamage(int damage){
        health -= damage;
    }

    public void attack() {
        float cycleTime = 0.7f;
        texture = new Texture("help_button.png");
        this.currentAnimation = new Animation(new TextureRegion(texture), 26, 5, 6, cycleTime);

        this.positionBeforeAnimation = new Vector2(this.position.x, this.position.y);



        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                position.x = positionBeforeAnimation.x;
                position.y = positionBeforeAnimation.y;
                texture = new Texture("charizard_front_spritesheet.png");
                currentAnimation = new Animation(new TextureRegion(texture), 143, 5, 29, 3.2f);
                busy = false;
            }
        }, cycleTime);

        this.busy = true;
    }

}
