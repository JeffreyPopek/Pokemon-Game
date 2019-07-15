package com.pokemongame.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pokemongame.game.pokemongame;

public class Player {
    private static final int MOVEMENT = 100;

    private PlayerDirection direction;
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

    public Player(int x, int y){
            direction = PlayerDirection.NONE;
        position = new Vector2(x,y);
        collided = false;
        velocity = new Vector2(0,0);
        texture = new Texture("pikachu_back_spritesheet.png");
        playerAnimation = new Animation(new TextureRegion(texture), 113, 5,23,3.2f);
        bounds = new Rectangle(position.x, position.y, texture.getWidth() / 3, texture.getHeight());
    }


    public void stand(){
        this.direction = PlayerDirection.NONE;
    }

    public void move(PlayerDirection direction){

        //System.out.println(position.x);
        if (direction == PlayerDirection.LEFT && position.x > 0){
            this.direction = direction;
        } else if ( direction == PlayerDirection.RIGHT && position.x < 190){
            this.direction = direction;
        }
    }

    public void update(float dt){
        playerAnimation.update(dt);

        velocity.scl(dt);

        if(direction == PlayerDirection.RIGHT) {
            position.add(MOVEMENT * dt, velocity.y);
        }
        else if(direction == PlayerDirection.LEFT){
            position.add(-MOVEMENT * dt, velocity.y);
        }

        velocity.scl(1/dt);

        bounds.setPosition(position.x, position.y);
    }





    public void dispose() {

        texture.dispose();
    }
}
