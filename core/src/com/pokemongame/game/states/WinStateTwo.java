package com.pokemongame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WinStateTwo extends State {
    private Texture winscreen;
    private Texture backbutton;
    private Texture arrow;
    public WinStateTwo(GameStateManager value) {
        super(value);
        backbutton = new Texture("menu_button.png");
        winscreen = new Texture("winscreen.png");
        arrow = new Texture("right_arrow.png");
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

        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > 262 && Gdx.input.getX() < 450
                    && Gdx.input.getY() < 695 && Gdx.input.getY() > 592) {
                gsm.set(new PlayStateThree(gsm));
            }
        }

    }

    public void render(SpriteBatch sb) {
        super.render(sb);

        sb.begin();

        sb.draw(winscreen, 0, 0, 240, 400);
        sb.draw(backbutton, 20, 20);
        sb.draw(arrow, 130, 20);

        sb.end();
    }

    @Override
    public void dispose() {

    }
}
