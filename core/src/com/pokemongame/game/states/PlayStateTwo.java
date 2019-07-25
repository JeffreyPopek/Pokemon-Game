package com.pokemongame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.pokemongame.game.pokemongame;
import com.pokemongame.game.sprites.Blastoise;
import com.pokemongame.game.sprites.Button;
import com.pokemongame.game.sprites.Blastoise;
import com.pokemongame.game.sprites.Electroball;
import com.pokemongame.game.sprites.Waterball;
import com.pokemongame.game.sprites.Fireblast;
import com.pokemongame.game.sprites.Paralyze;
import com.pokemongame.game.sprites.Player;
import com.pokemongame.game.sprites.Thunderbolt;
import com.pokemongame.game.sprites.PlayerDirection;
import java.util.Random;
import com.badlogic.gdx.audio.Music;


import java.util.ArrayList;
import java.util.List;

import static com.pokemongame.game.states.PlayingState.ATTACKING;
import static com.pokemongame.game.states.PlayingState.DEFENDING;
import static java.lang.Thread.sleep;

public class PlayStateTwo extends State {

    private final String ENEMY = "ENEMY";
    private final String PLAYER = "PLAYER";
    private String currentActor = ENEMY;

    private Array<Message> messages;
    private Message currentMessage;
    private PlayingState playingState;
    private Player player;
    private boolean isWaitingForUser;
    private Blastoise blastoise;
    private BitmapFont font;
    private Texture background;
    private Texture ground;
    private Vector2 groundPosition1, groundPosition2;
    private Waterball waterball;
    private List <Waterball> waterballs;
    private Button attack1;
    private Button attack2;
    private Thunderbolt lightning;
    private boolean ButtonClicks;
    private Random number;
    private Music music;
    private Fireblast enemyattack2;
    private int pattern;
    private Electroball electroball;
    private boolean skipturn;
    private int paralyze;
    private Paralyze paralyzestatus;


    public PlayStateTwo(GameStateManager gsm) {
        super(gsm);
        playingState = PlayingState.INTRO;

        waterballs = new ArrayList<Waterball>();
        paralyze = 6;
        player = new Player(80, 120);
        blastoise = new Blastoise(80, 295);
        background = new Texture("bg2.png");
        attack1 = new Button(1, 25, "Thunderbolt_button.png");
        attack2 = new Button(100, 25, "electroball_button.png");
        ButtonClicks = false;
        font = new BitmapFont();
        font.getData().setScale(1.5f);
        number = new Random();
        skipturn = false;


        loadIntroMessages();
        music = Gdx.audio.newMusic(Gdx.files.internal("playstate_music.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
    }

    public void loadIntroMessages(){
        messages = new Array<Message>();
        messages.add(new Message(PLAYER, "OK..."));
        messages.add(new Message(ENEMY, "Let's play!"));
        getNextMessage();
    }

    private void beginBattle(){
        this.playingState = DEFENDING;
        fight();
    }

    private void enemyFight(){

        if (currentActor == ENEMY && !(blastoise.isBusy() || player.isBusy())) {
            playingState = DEFENDING;
            blastoise.attack();
            pattern = number.nextInt(5);
            //pattern = 3;

//            fireball1 = new Fireball(blastoiseetPosition().x, blastoise.getPosition().y);

            if (pattern == 0) {
                waterballs.add(new Waterball(20, blastoise.getPosition().y));
                waterballs.add(new Waterball(60, blastoise.getPosition().y));
                waterballs.add(new Waterball(100, blastoise.getPosition().y));
                waterballs.add(new Waterball(200, blastoise.getPosition().y));

            } else if (pattern == 1) {
                waterballs.add(new Waterball(200, blastoise.getPosition().y));
                waterballs.add(new Waterball(100, blastoise.getPosition().y));
                waterballs.add(new Waterball(150, blastoise.getPosition().y));


            } else if (pattern == 2) {
                waterballs.add(new Waterball(10, blastoise.getPosition().y));
                waterballs.add(new Waterball(60, blastoise.getPosition().y));
                waterballs.add(new Waterball(100, blastoise.getPosition().y));
                waterballs.add(new Waterball(140, blastoise.getPosition().y));


            } else if (pattern == 3) {
                enemyattack2 = new Fireblast(2, 250);

            }else if(pattern == 4){
                enemyattack2 = new Fireblast(100, 250);
            }




            messages.add(new Message(ENEMY, "Opponent's turn"));
            getNextMessage();

            currentActor = PLAYER;
        }


    }

    private void playerFight(){
        if(currentActor == PLAYER && !(blastoise.isBusy() || player.isBusy())) {
            playingState = ATTACKING;
            player.setPosition(80, 120);
            player.attack();

            messages.add(new Message(PLAYER, "Your turn"));
            getNextMessage();

            currentActor = ENEMY;
        }
    }

    private void fight(){
        enemyFight();
        playerFight();
    }

    private void resolveWinner(){

    }

    @Override
    public void handleInput() {

        if(Gdx.input.justTouched()){
            System.out.println(Gdx.input.getX() +", "+ Gdx.input.getY());
            switch (playingState){
                case INTRO:
                    isWaitingForUser = false;
                    getNextMessage();
                    if(this.currentMessage == null){
                        this.beginBattle();
                    }
                    break;
                case ATTACKING:

                    isWaitingForUser = false;
                    if(Gdx.input.getX() < 157 && Gdx.input.getX() > attack1.getPosition().x && Gdx.input.getY() > 577 && Gdx.input.getY() < 645){
                        System.out.println("button clicked");
                        if(ButtonClicks == false) {
                            player.attack();
                            lightning = new Thunderbolt(35, 120);
                            blastoise.takeDamage(lightning.getDamage());
                            ButtonClicks = true;
                        }

                    }
                    if(Gdx.input.getX() < 355 && Gdx.input.getX() > attack2.getPosition().x && Gdx.input.getY() > 577 && Gdx.input.getY() < 645) {
                        System.out.println("button clicked");
                        if (ButtonClicks == false) {
                            player.attack();
                            electroball = new Electroball(85, 150);
                            blastoise.takeDamage(electroball.getDamage());
                            ButtonClicks = true;
                            paralyze = number.nextInt(5);
                            paralyzestatus = new Paralyze(100, 260);
                        }
                    }
                    break;
                case DEFENDING:


                    break;
                default:
                    break;
            }
        } else if (Gdx.input.isTouched()){
            switch (playingState){
                case DEFENDING:
                    player.move(getMoveDirection());
                    break;
                default:
                    break;

            }
        } else {
            player.stand();
        }

    }

    private PlayerDirection getMoveDirection (){
        PlayerDirection direction = PlayerDirection.RIGHT;
        if (Gdx.input.getX() < pokemongame.WIDTH / 2){
            direction = PlayerDirection.LEFT;
        }
        return direction;
    }

    private void getNextMessage(){
        if (messages.size > 0){
            this.isWaitingForUser = true;
            currentMessage = messages.pop();
        } else {
            currentMessage = null;
        }


    }

    @Override
    public void update(float dt) {
        super.update(dt);
        player.update(dt);
        if(player.getHealth() <= 0){
            gsm.set(new GameOverState(gsm));
        }

        if(blastoise.getHealth() <= 0){
            gsm.set(new WinTwoState(gsm));
        }
        blastoise.update(dt);
        if(playingState == DEFENDING){
            if(enemyattack2 != null){
                enemyattack2.update(dt);
                if(enemyattack2.collides(player.getBounds())){
                    player.takeDamage(enemyattack2.getDamage());
                }
            }else {
                for (int i = 0; i < waterballs.size(); i++) {
                    waterballs.get(i).update(dt);
                    if (waterballs.get(i).collides(player.getBounds())) {
                        player.takeDamage(waterballs.get(i).getDamage());
                    }
                }
            }
        }
        if(waterballs.size() > 0) {
            if (waterballs.get(0).getPosition().y < 0) {
                fight();
                waterballs.clear();
                ButtonClicks = false;
                lightning = null;
                electroball = null;


            }
        }
        if(enemyattack2 != null) {


            if (enemyattack2.getPosition().y < 0) {
                fight();
                enemyattack2 = null;
                lightning = null;
                ButtonClicks = false;
                electroball = null;
            }
        }
        if(playingState == ATTACKING && lightning != null){
            lightning.update(dt);
            fight();
            paralyzestatus = null;
            System.out.println("LIGHTNING");
        }

        if(playingState == ATTACKING && electroball != null){
            electroball.update(dt);
            //fight();

            System.out.println(paralyze);
            if(paralyze == 0){
                ButtonClicks = false;
                playerFight();
                //System.out.println("skipped");
            }else {
                fight();
                paralyzestatus = null;
            }


        }
        if(paralyze != 0){

        }

    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

        sb.begin();
        // Start Drawing
        sb.draw(background, cam.position.x - (cam.viewportWidth/2), 0);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        sb.draw(blastoise.getTexture(), blastoise.getPosition().x, blastoise.getPosition().y);


        if(playingState == DEFENDING){
            if(enemyattack2 != null){
                sb.draw(enemyattack2.getTexture(), enemyattack2.getPosition().x, enemyattack2.getPosition().y);
            }else {
                for(int i = 0; i < waterballs.size(); i++) {
                    sb.draw(waterballs.get(i).getTexture(), waterballs.get(i).getPosition().x, waterballs.get(i).getPosition().y, 40, 40);
                }
                //System.out.println(fireballs.get(i).getPosition().y);
            }
//            sb.draw(fireball1.getTexture(), fireball1.getPosition().x, fireball1.getPosition().y);
            //System.out.println("sdssd");
        }
        if(playingState == ATTACKING && lightning != null){
            sb.draw(lightning.getTexture(), lightning.getPosition().x, lightning.getPosition().y, 140, 400);
        }
        sb.draw(attack1.getTexture(), attack1.getPosition().x, attack1.getPosition().y, 120, 120);
        if(playingState == ATTACKING && electroball != null) {
            sb.draw(electroball.getTexture(), electroball.getPosition().x, electroball.getPosition().y, 40, 40);

        }
        sb.draw(attack2.getTexture(), attack2.getPosition().x, attack2.getPosition().y, 120, 120);

        if(paralyzestatus != null){
            sb.draw(paralyzestatus.getTexture(), 100, 260);
        }


        this.renderMessageOnSpriteBatch(sb);
        this.renderLifeTotal(sb);
        sb.end();

    }

    private void renderMessageOnSpriteBatch(SpriteBatch sb){
        font.setColor(255,255,255,1);
        if (this.currentMessage != null) {
            if (this.currentMessage.getSender() == PLAYER){
                font.draw(sb, this.currentMessage.getMessage(), 75, 395);
            } else {
                font.draw(sb, this.currentMessage.getMessage(), 75, 395);
            }

        }
    }

    private void renderLifeTotal(SpriteBatch sb){
        font.setColor(0,255,0,1);
        font.draw(sb, String.valueOf(player.getHealth()), 2, 20);
        font.setColor(255,0,0,1);
        font.draw(sb, String.valueOf(blastoise.getHealth()), 2, 395);
    }
    @Override
    public void dispose() {
        background.dispose();
        player.dispose();
        blastoise.dispose();
        music.dispose();

        System.out.println("Play State Disposed");
    }

    private void updateGround(){
        if (cam.position.x - (cam.viewportWidth / 2) > groundPosition1.x + ground.getWidth()){
            groundPosition1.add(ground.getWidth() * 2, 0);

        }
        if (cam.position.x - (cam.viewportWidth / 2) > groundPosition2.x + ground.getWidth()){
            groundPosition2.add(ground.getWidth() * 2, 0);
        }
    }
}
