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


    public LevelsState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("level_state.png");
        backButton = new Texture("menu_button.png");
        charizard = new Texture("charizard_front.png");
        blastoise = new Texture("blastoise_front.png");
        venusaur = new Texture("venusaur_front.png");
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
            if (Gdx.input.getX() > 139 && Gdx.input.getX() < 299
                    && Gdx.input.getY() < 595 && Gdx.input.getY() > 475) {
                gsm.set(new PlayStateThree(gsm));
            }
        }
//        if(Gdx.input.getX() > 170 && Gdx.input.getX() < 336
//                && Gdx.input.getY() < 381 && Gdx.input.getY() > 293){
//            gsm.set(new PlayState(gsm));

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

        sb.draw(venusaur, 65, 75);

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        backButton.dispose();
        System.out.println("Level State Disposed");
    }
}
