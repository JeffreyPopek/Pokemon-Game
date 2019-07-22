package com.pokemongame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverState extends State{
    private Texture gameover;
    private Texture backbutton;

    public GameOverState(GameStateManager value) {
        super(value);
        gameover = new Texture("game_over.png");
        backbutton = new Texture("menu_button.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new MenuState(gsm));
        }
    }


    public void render(SpriteBatch sb) {
        super.render(sb);

        sb.begin();

        sb.draw(gameover, 0, 0, 240, 400);
        sb.draw(backbutton, 20, 20);

        sb.end();
    }
    @Override
    public void dispose() {

    }
}
