package com.pokemongame.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.pokemongame.game.pokemongame;

public class Player {
    private static final int MOVEMENT = 160;

    private PlayerDirection direction;
    private Rectangle bounds;
    private Animation currentAnimation;
    private Texture texture;
    private Vector2 position;
    private Vector2 positionBeforeAnimation;
    private Vector2 velocity;
    private int health;
    private boolean collided;
    private boolean busy;


    public void setCollided(boolean collided) {
        this.collided = collided;
    }
    public Rectangle getBounds() {
        return bounds;
    }

    public void takeDamage(int damage){
        health -= damage;
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

    public Player(int x, int y){
        health = 1000;
        direction = PlayerDirection.NONE;
        position = new Vector2(x,y);
        collided = false;
        velocity = new Vector2(0,0);
        texture = new Texture("pikachu_back_spritesheet.png");
        currentAnimation = new Animation(new TextureRegion(texture), 113, 5,23,3.2f);
        bounds = new Rectangle(position.x, position.y, texture.getWidth() / 5, texture.getHeight()/23);
    }


    public void move(PlayerDirection direction){
        this.direction = direction;
    }



    public void stand() {
        this.direction = PlayerDirection.NONE;
    }

    public void update(float dt){
        if (currentAnimation != null){
            currentAnimation.update(dt);
        }


        velocity.scl(dt);


        if(direction == PlayerDirection.RIGHT && position.x < 190) {
            position.add(MOVEMENT * dt, velocity.y);
        }
        else if(direction == PlayerDirection.LEFT && position.x > 0){
            position.add(-MOVEMENT * dt, velocity.y);
        } else {
            this.direction = PlayerDirection.NONE;
        }

        velocity.scl(1/dt);

        bounds.setPosition(position.x, position.y);
    }


    public boolean isBusy() {
        return busy;
    }

    public void setPosition(int x, int y){
        this.position.x = x;
        this.position.y = y;
    }

    public void attack() {
        float cycleTime = 1f;
        texture = new Texture("pikachu_back_spritesheet.png");
        this.currentAnimation =  new Animation(new TextureRegion(texture), 113, 5,23,cycleTime);

        this.positionBeforeAnimation = new Vector2(this.position.x,this.position.y);


        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                position.x = positionBeforeAnimation.x;
                position.y = positionBeforeAnimation.y;
                texture = new Texture("pikachu_back_spritesheet.png");
                currentAnimation = new Animation(new TextureRegion(texture), 113, 5,23,3.2f);
                busy = false;
            }
        }, cycleTime);

        this.busy = true;
    }


    public void dispose() {

        texture.dispose();
    }




}
