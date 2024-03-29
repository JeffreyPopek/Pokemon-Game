    package com.pokemongame.game.sprites;

    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.graphics.g2d.TextureRegion;
    import com.badlogic.gdx.math.Rectangle;
    import com.badlogic.gdx.math.Vector2;

    public class Thunderbolt {

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

            public Thunderbolt(float x, float y, int newDamage){
                damage = newDamage;
                position = new Vector2(x, y);
                velocity = new Vector2(0, -100);
                texture = new Texture("lightning_spritesheet.png");
                currentAnimation = new Animation(new TextureRegion(texture),  4, 5, 2, 1.5f);
                bounds = new Rectangle(position.x, position.y, 40, 40);
            }


            public void update(float dt) {

                if (currentAnimation != null) {
                    currentAnimation.update(dt);
                }
                // position.add(0, velocity.y * dt);


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
