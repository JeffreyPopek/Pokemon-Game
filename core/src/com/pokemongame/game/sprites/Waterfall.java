package com.pokemongame.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Waterfall {
    private Animation currentAnimation;
    private Texture texture;
    private Vector2 position;
    private Vector2 velocity;
    private Rectangle bounds;
    private int damage;

    public Rectangle getBounds() {
        return bounds;
    }
    public TextureRegion getTexture() {
        return currentAnimation.getFrame();
    }
    public Vector2 getPosition() {
        return position;
    }

    public Waterfall(float x, float y){
        damage = 25;
        position = new Vector2(x, y);
        velocity = new Vector2(0, -100);
        texture = new Texture("waterfall.png");
        currentAnimation = new Animation(new TextureRegion(texture),  12, 5, 3, 1.0f);
        bounds = new Rectangle(position.x , position.y, texture.getWidth() /5, texture.getHeight()/12);
    }

    public void update(float dt) {

        if (currentAnimation != null) {
            currentAnimation.update(dt);
        }
        position.add(0, velocity.y * dt);


        bounds.setPosition(getPosition().x, getPosition().y);
    }



    public void dispose() {
        texture.dispose();
    }

    public int getDamage() {
        return damage;
    }
    public boolean collides(Rectangle player){
        return player.overlaps(bounds);
    }

}

