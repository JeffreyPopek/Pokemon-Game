package com.pokemongame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WinStateThree extends State {
    private Texture winscreen;
    private Texture backbutton;
    private Texture arrow;
    public WinStateThree(GameStateManager value) {
        super(value);
        backbutton = new Texture("menu_button.png");
        winscreen = new Texture("winscreen.png");
        arrow = new Texture("right_arrow.png");
    }

    @Override
    public void handleInput() {
        mouse.set(Gdx.input.getX()*2, (Gdx.graphics.getHeight()- Gdx.input.getY()*2), 0);
        cam.unproject(mouse);
        if(Gdx.input.justTouched()) {
            System.out.println(Gdx.input.getX() + ", " + Gdx.input.getY());
        }
        if (Gdx.input.justTouched()) {
            if (mouse.x > 41 && mouse.x < 231
                    && mouse.y < 705 && mouse.y > 591) {
                gsm.set(new MenuState(gsm));
            }
        }

        if (Gdx.input.justTouched()) {
            if (mouse.x > 262 && mouse.x < 450
                    && mouse.y < 695 && mouse.y > 592) {
                gsm.set(new PlayStateFour(gsm));
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
