package com.pokemongame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pokemongame.game.pokemongame;

import javax.xml.soap.Text;

public class LevelsState extends State {

    private Texture background;
    private Texture backButton;
    private Texture charizard;
    private Texture blastoise;
    private Texture venusaur;
    private Texture mewtwo;


    public LevelsState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("levels_state.png");
        backButton = new Texture("menu_button.png");
        charizard = new Texture("charizard_front.png");
        blastoise = new Texture("blastoise_front.png");
        venusaur = new Texture("venusaur_front.png");
        mewtwo = new Texture("mewtwo_front.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            if(Gdx.input.getX() > 41 && Gdx.input.getX() < 231
                    && Gdx.input.getY() < 730 && Gdx.input.getY() > 616){
                gsm.set(new MenuState(gsm));
            }
            System.out.println("mouseY "+ Gdx.input.getY());
            System.out.println("mouseX "+ Gdx.input.getX());


        }
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > 7 && Gdx.input.getX() < 154
                    && Gdx.input.getY() < 366 && Gdx.input.getY() > 243) {
                gsm.set(new PlayState(gsm));
            }
        }

        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > 295 && Gdx.input.getX() < 438
                    && Gdx.input.getY() < 343 && Gdx.input.getY() > 248) {
                gsm.set(new PlayStateTwo(gsm));
            }
        }

        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > 3 && Gdx.input.getX() < 164
                    && Gdx.input.getY() < 601 && Gdx.input.getY() > 472) {
                gsm.set(new PlayStateThree(gsm));
            }
        }

        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > 289 && Gdx.input.getX() < 456
                    && Gdx.input.getY() < 603 && Gdx.input.getY() > 480) {
                gsm.set(new PlayStateFour(gsm));
            }
        }

    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

        sb.begin();
        sb.draw(background, 0, 0, 240, 400);

        sb.draw(backButton, 20, 10);

        sb.draw(charizard, -5, 200);

        sb.draw(blastoise, 140, 200);

        sb.draw(venusaur, -5, 75);

        sb.draw(mewtwo, 140, 75);

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        backButton.dispose();
        System.out.println("Level State Disposed");
    }
}
