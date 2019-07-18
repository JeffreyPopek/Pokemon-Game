package com.pokemongame.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EnemyAttack {
    private Animation currentAnimation;
    private Texture texture;
    private Vector2 position;
    private Vector2 velocity;
    private Rectangle bounds;

    public TextureRegion getTexture() {
        return currentAnimation.getFrame();
    }
    public Vector2 getPosition() {
        return position;
    }

    public EnemyAttack(float x, float y){
        position = new Vector2(x, y);
        velocity = new Vector2(0, -100);
        texture = new Texture("charizard_front_spritesheet.png");
        currentAnimation = new Animation(new TextureRegion(texture),  143, 5, 29, 3.2f);
    }

    public void update(float dt) {

        if (currentAnimation != null) {
            currentAnimation.update(dt);
        }
        position.add(0, velocity.y * dt);


//        bounds.setPosition(getPosition().x, getPosition().y);
    }



    public void dispose() {
        texture.dispose();
    }


}
