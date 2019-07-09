package com.pokemongame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.pokemongame.game.sprites.Player;

public class PlayState extends State {



    private Player player;
    private Texture background;
    private Texture ground;
    private Vector2 groundPosition1, groundPosition2;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        player = new Player(50, 300);
        background = new Texture("bg.png");
    }



    @Override
    public void handleInput() {


    }

    @Override
    public void update(float dt) {
        super.update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

        sb.begin();
        // Start Drawing
        sb.draw(background, cam.position.x - (cam.viewportWidth/2), 0);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);


        sb.draw(ground, groundPosition1.x, groundPosition1.y);
        sb.draw(ground, groundPosition2.x, groundPosition2.y);

        // Stop Drawing

        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        player.dispose();


        System.out.println("Play State Disposed");
    }

    private void updateGround(){
        if (cam.position.x - (cam.viewportWidth / 2) > groundPosition1.x + ground.getWidth()){
            groundPosition1.add(ground.getWidth() * 2, 0);

        }
        if (cam.position.x - (cam.viewportWidth / 2) > groundPosition2.x + ground.getWidth()){
            groundPosition2.add(ground.getWidth() * 2, 0);

        }
    }
}
