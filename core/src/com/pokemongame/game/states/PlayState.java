package com.pokemongame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.pokemongame.game.pokemongame;
import com.pokemongame.game.sprites.Animation;
import com.pokemongame.game.sprites.Button;
import com.pokemongame.game.sprites.Charizard;
import com.pokemongame.game.sprites.EnemyAttack;
import com.pokemongame.game.sprites.EnemyAttackTwo;
import com.pokemongame.game.sprites.Player;
import com.pokemongame.game.sprites.PlayerAttack;
import com.pokemongame.game.sprites.PlayerDirection;
import java.util.Random;
import com.badlogic.gdx.audio.Music;


import java.util.ArrayList;
import java.util.List;

import static com.pokemongame.game.states.PlayingState.ATTACKING;
import static com.pokemongame.game.states.PlayingState.DEFENDING;
import static java.lang.Thread.sleep;

public class PlayState extends State {

    private final String ENEMY = "ENEMY";
    private final String PLAYER = "PLAYER";
    private String currentActor = ENEMY;

    private Array<Message> messages;
    private Message currentMessage;
    private PlayingState playingState;
    private Player player;
    private boolean isWaitingForUser;
    private Charizard charizard;
    private BitmapFont font;
    private Texture background;
    private Texture ground;
    private Vector2 groundPosition1, groundPosition2;
    private EnemyAttack fireball1;
    private List < EnemyAttack > fireballs;
    private Button attack1;
    private PlayerAttack lightning;
    private boolean ButtonClicks;
    private Random number;
    private Music music;
    private EnemyAttackTwo enemyattack2;
    private int pattern;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        playingState = PlayingState.INTRO;

        fireballs = new ArrayList<EnemyAttack>();
        player = new Player(80, 120);
        charizard = new Charizard(80, 295);
        background = new Texture("bg.png");
        attack1 = new Button(1, 25, "Thunderbolt_button.png");
        ButtonClicks = false;
        font = new BitmapFont();
        font.getData().setScale(1.5f);
        number = new Random();

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
        if (currentActor == ENEMY && !(charizard.isBusy() || player.isBusy())) {
            playingState = DEFENDING;
            charizard.attack();
            pattern = number.nextInt(4);
            //pattern = 3;

//            fireball1 = new EnemyAttack(charizard.getPosition().x, charizard.getPosition().y);

            if(pattern == 0){
                fireballs.add(new EnemyAttack(20, charizard.getPosition().y));
                fireballs.add(new EnemyAttack(60, charizard.getPosition().y));
                fireballs.add(new EnemyAttack(100, charizard.getPosition().y));
                fireballs.add(new EnemyAttack(200, charizard.getPosition().y));

            }else if(pattern == 1){
                fireballs.add(new EnemyAttack(200, charizard.getPosition().y));
                fireballs.add(new EnemyAttack(100, charizard.getPosition().y));
                fireballs.add(new EnemyAttack(150, charizard.getPosition().y));


            }else if(pattern == 2){
                fireballs.add(new EnemyAttack(10, charizard.getPosition().y));
                fireballs.add(new EnemyAttack(60, charizard.getPosition().y));
                fireballs.add(new EnemyAttack(100, charizard.getPosition().y));
                fireballs.add(new EnemyAttack(140, charizard.getPosition().y));


            } else if(pattern == 3){
                enemyattack2 = new EnemyAttackTwo(60, 250);
            }

            messages.add(new Message(ENEMY, "Opponent's turn"));
            getNextMessage();

            currentActor = PLAYER;
        }
    }

    private void playerFight(){
        if(currentActor == PLAYER && !(charizard.isBusy() || player.isBusy())) {
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
                            lightning = new PlayerAttack(35, 120);
                            charizard.takeDamage(lightning.getDamage());
                            ButtonClicks = true;
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

        if(charizard.getHealth() <= 0){
            gsm.set(new WinState(gsm));
        }
        charizard.update(dt);
        if(playingState == DEFENDING){
            if(pattern == 3){
                enemyattack2.update(dt);
                if(enemyattack2.collides(player.getBounds())){
                    player.takeDamage(enemyattack2.getDamage());
                }
            }else {
                for (int i = 0; i < fireballs.size(); i++) {
                    fireballs.get(i).update(dt);
                    if (fireballs.get(i).collides(player.getBounds())) {
                        player.takeDamage(fireballs.get(i).getDamage());
                    }
                }
            }
        }
        if(fireballs.size() > 0) {
            if (fireballs.get(0).getPosition().y < 0) {
                fight();
                fireballs.clear();
                ButtonClicks = false;
                lightning = null;

            }
        }
        if(enemyattack2 != null) {


            if (enemyattack2.getPosition().y < 0) {
                fight();
                enemyattack2 = null;
                lightning = null;
                ButtonClicks = false;
            }
        }
        if(playingState == ATTACKING && lightning != null){
            lightning.update(dt);
            fight();
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

        sb.begin();
        // Start Drawing
        sb.draw(background, cam.position.x - (cam.viewportWidth/2), 0);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        sb.draw(charizard.getTexture(), charizard.getPosition().x, charizard.getPosition().y);


        if(playingState == DEFENDING){
            if(pattern == 3){
                sb.draw(enemyattack2.getTexture(), enemyattack2.getPosition().x, enemyattack2.getPosition().y);
            }else {
                for(int i = 0; i < fireballs.size(); i++) {
                    sb.draw(fireballs.get(i).getTexture(), fireballs.get(i).getPosition().x, fireballs.get(i).getPosition().y, 40, 40);
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
        font.draw(sb, String.valueOf(charizard.getHealth()), 2, 395);
    }
    @Override
    public void dispose() {
        background.dispose();
        player.dispose();
        charizard.dispose();
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
