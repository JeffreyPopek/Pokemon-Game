package com.pokemongame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WinState extends State {
    private Texture winscreen;
    private Texture backbutton;
    public WinState(GameStateManager value) {
        super(value);
        backbutton = new Texture("menu_button.png");
        winscreen = new Texture("winscreen.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            System.out.println(Gdx.input.getX() + ", " + Gdx.input.getY());
        }
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > 41 && Gdx.input.getX() < 231
                    && Gdx.input.getY() < 705 && Gdx.input.getY() > 591) {
                gsm.set(new MenuState(gsm));
            }
        }

    }

    public void render(SpriteBatch sb) {
        super.render(sb);

        sb.begin();

        sb.draw(winscreen, 0, 0, 240, 400);
        sb.draw(backbutton, 20, 20);

        sb.end();
    }

    @Override
    public void dispose() {

    }
}
