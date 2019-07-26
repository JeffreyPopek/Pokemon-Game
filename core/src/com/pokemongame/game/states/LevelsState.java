package com.pokemongame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
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
            mouse.set(Gdx.input.getX()*2, (Gdx.graphics.getHeight()- Gdx.input.getY()*2), 0);
            cam.unproject(mouse);
            if(mouse.x > 41 && mouse.x < 231
                    && mouse.y < 730 && mouse.y > 616){
                gsm.set(new MenuState(gsm));
            }
            System.out.println("mouseY "+ Gdx.input.getY());
            System.out.println("mouseX "+ Gdx.input.getX());


        }
        if (Gdx.input.justTouched()) {
            if (mouse.x > 7 && mouse.x < 154
                    && mouse.y < 366 && mouse.y > 243) {
                gsm.set(new PlayState(gsm));
            }
        }

        if (Gdx.input.justTouched()) {
            if (mouse.x > 295 && mouse.x < 438
                    && mouse.y < 343 && mouse.y > 248) {
                gsm.set(new PlayStateTwo(gsm));
            }
        }

        if (Gdx.input.justTouched()) {
            if (mouse.x > 3 && mouse.x < 164
                    && mouse.y < 601 && mouse.y > 472) {
                gsm.set(new PlayStateThree(gsm));
            }
        }

        if (Gdx.input.justTouched()) {
            if (mouse.x > 289 && mouse.x < 456
                    && mouse.y < 603 && mouse.y > 480) {
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
