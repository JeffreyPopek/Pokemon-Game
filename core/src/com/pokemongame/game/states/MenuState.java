package com.pokemongame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pokemongame.game.pokemongame;

import javax.xml.soap.Text;

public class MenuState extends State {

    private Texture background;
    private Texture playButton;
    private Texture settingsButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg2.jpg");
        playButton = new Texture("playbtn.png");
        settingsButton = new Texture("settings_button.png");

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            if(Gdx.input.getX() > 170 && Gdx.input.getX() < 336
                && Gdx.input.getY() < 381 && Gdx.input.getY() > 293){
                gsm.set(new PlayState(gsm));
            }
            System.out.println("mouseY "+ Gdx.input.getY());
            System.out.println("mouseX "+ Gdx.input.getX());


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
        sb.draw(background, 0, 0);
        sb.draw(playButton,
                cam.position.x - playButton.getWidth() / 2,
                cam.position.y);
//        System.out.println("settingsbuttonWidth; "+playButton.getWidth()); //104
//        System.out.println("settingsbuttonHeight; "+playButton.getHeight()); //59
        sb.draw(settingsButton, 2, 20);

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
        System.out.println("Menu State Disposed");
    }
}
