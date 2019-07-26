package com.pokemongame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class HelpState extends State {
    private Texture attackscreen;
    private Texture backbutton;

    public HelpState(GameStateManager value) {
        super(value);
        backbutton = new Texture("menu_button.png");
        attackscreen = new Texture("Attack_State.png");
    }

    @Override
    public void handleInput() {
        mouse.set(Gdx.input.getX()*2, (Gdx.graphics.getHeight()- Gdx.input.getY()*2), 0);
        cam.unproject(mouse);
        if(Gdx.input.justTouched()) {
            System.out.println(Gdx.input.getX() + ", " + Gdx.input.getY());
        }
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > 21 && Gdx.input.getX() < 158
                    && Gdx.input.getY() < 735 && Gdx.input.getY() > 654) {
                gsm.set(new MenuState(gsm));
            }
        }

    }

    public void render(SpriteBatch sb) {
        super.render(sb);

        sb.begin();

        sb.draw(attackscreen, 0, 0, 240, 400);
        sb.draw(backbutton, 10, 10, 70, 70);

        sb.end();
    }

    @Override
    public void dispose() {

    }
}
