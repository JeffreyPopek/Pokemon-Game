package com.pokemongame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.pokemongame.game.pokemongame;

import javax.xml.soap.Text;

public class MenuState extends State {

    private Texture background;
   // private Music music;
    private Texture playButton;
    private Texture helpButton;
    private Texture levelsButton;
    private Texture title;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
        helpButton = new Texture("help_button.png");
        levelsButton = new Texture("levels_button.png");
        title = new Texture("title.png");

//        music = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));
//        music.setLooping(true);
//        music.setVolume(0.1f);
//        music.play();
    }

    @Override
    public void handleInput() {

        if (Gdx.input.justTouched()){
            mouse.set(Gdx.input.getX()*2, (Gdx.graphics.getHeight()- Gdx.input.getY()*2), 0);
            cam.unproject(mouse);
            if(mouse.x > 170 && mouse.x < 336
                && mouse.y < 381 && mouse.y > 293){
                gsm.set(new PlayState(gsm));
            }
            System.out.println("mouseY "+ Gdx.input.getY());
            System.out.println("mouseX "+ Gdx.input.getX());


        }
            if(Gdx.input.justTouched()) {

            if(mouse.x > 171 && mouse.x < 330
                    && mouse.y < 736 && mouse.y > 628){
                gsm.set(new LevelsState(gsm));
            }
            }

        if (Gdx.input.justTouched()) {
            if (mouse.x > 10 && mouse.x < 98
                    && mouse.y < 734 && mouse.y > 651) {
                gsm.set(new HelpState(gsm));
            }
            //help button
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
        sb.draw(helpButton, 2, 7, 80, 80);
        sb.draw(title, 20, 250);
        sb.draw(levelsButton, 85, 15);

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
        //music.dispose();
        System.out.println("Menu State Disposed");
    }
}
