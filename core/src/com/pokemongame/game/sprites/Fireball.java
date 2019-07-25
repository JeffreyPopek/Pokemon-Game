package com.pokemongame.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Fireball {
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

    public Fireball(float x, float y){
        damage = 5;
        position = new Vector2(x, y);
        velocity = new Vector2(0, -100);
        texture = new Texture("fireball_sheet.png");
        currentAnimation = new Animation(new TextureRegion(texture),  10, 5, 2, 2.0f);
        bounds = new Rectangle(position.x, position.y, 40, 40);
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

