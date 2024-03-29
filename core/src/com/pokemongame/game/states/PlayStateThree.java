package com.pokemongame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.pokemongame.game.pokemongame;
import com.pokemongame.game.sprites.Button;
import com.pokemongame.game.sprites.Electroball;
import com.pokemongame.game.sprites.Leaf;
import com.pokemongame.game.sprites.Fireblast;
import com.pokemongame.game.sprites.Paralyze;
import com.pokemongame.game.sprites.Player;
import com.pokemongame.game.sprites.Thunderbolt;
import com.pokemongame.game.sprites.PlayerDirection;
import java.util.Random;
import com.badlogic.gdx.audio.Music;
import com.pokemongame.game.sprites.Venusaur;


import java.util.ArrayList;
import java.util.List;

import static com.pokemongame.game.states.PlayingState.ATTACKING;
import static com.pokemongame.game.states.PlayingState.DEFENDING;
import static java.lang.Thread.sleep;

public class PlayStateThree extends State {

    private final String ENEMY = "ENEMY";
    private final String PLAYER = "PLAYER";
    private String currentActor = ENEMY;

    private Array<Message> messages;
    private Message currentMessage;
    private PlayingState playingState;
    private Player player;
    private boolean isWaitingForUser;
    private Venusaur venusaur;
    private BitmapFont font;
    private Texture background;
    private Texture ground;
    private Vector2 groundPosition1, groundPosition2;
    private Leaf leaf;
    private List <Leaf> leaves;
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
    private Texture returnmenu;



    public PlayStateThree(GameStateManager gsm) {
        super(gsm);
        playingState = PlayingState.INTRO;

        leaves = new ArrayList<Leaf>();
        paralyze = 6;
        player = new Player(80, 120);
        venusaur = new Venusaur(80, 295);
        background = new Texture("bg3.png");
        attack1 = new Button(1, 25, "thunderbolt_button.png");
        attack2 = new Button(100, 25, "electroball_button.png");
        ButtonClicks = false;
        font = new BitmapFont();
        font.getData().setScale(1.5f);
        number = new Random();
        skipturn = false;
        player.setHealth(3000);
        returnmenu = new Texture("return_menu_button.png");


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

        if (currentActor == ENEMY && !(venusaur.isBusy() || player.isBusy())) {
            playingState = DEFENDING;
            venusaur.attack();
            pattern = number.nextInt(4);
            //pattern = 3;

//            leaves = new leaves(charizard.getPosition().x, charizard.getPosition().y);

            if (pattern == 0) {
                leaves.add(new Leaf(20, venusaur.getPosition().y));
                leaves.add(new Leaf(60, venusaur.getPosition().y));
                leaves.add(new Leaf(100, venusaur.getPosition().y));
                leaves.add(new Leaf(200, venusaur.getPosition().y));


            } else if (pattern == 1) {
                leaves.add(new Leaf(200, venusaur.getPosition().y));
                leaves.add(new Leaf(100, venusaur.getPosition().y));
                leaves.add(new Leaf(150, venusaur.getPosition().y));


            } else if (pattern == 2) {
                leaves.add(new Leaf(10, venusaur.getPosition().y));
                leaves.add(new Leaf(60, venusaur.getPosition().y));
                leaves.add(new Leaf(100, venusaur.getPosition().y));
                leaves.add(new Leaf(140, venusaur.getPosition().y));


            } else if (pattern == 3) {
                leaves.add(new Leaf(25, venusaur.getPosition().y));
                leaves.add(new Leaf(100, venusaur.getPosition().y));
                leaves.add(new Leaf(400, venusaur.getPosition().y));
                leaves.add(new Leaf(300, venusaur.getPosition().y+40));
                leaves.add(new Leaf(100, venusaur.getPosition().y+40));
                leaves.add(new Leaf(10, venusaur.getPosition().y+40));
                leaves.add(new Leaf(200, venusaur.getPosition().y+100));
                leaves.add(new Leaf(400, venusaur.getPosition().y+100));
                leaves.add(new Leaf(50, venusaur.getPosition().y+100));

//                leaves.add(new Leaf(, venusaur.getPosition().y+100));
            }



            messages.add(new Message(ENEMY, "Opponent's turn"));
            getNextMessage();

            currentActor = PLAYER;
        }


    }

    private void playerFight(){
        if(currentActor == PLAYER && !(venusaur.isBusy() || player.isBusy())) {
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
        mouse.set(Gdx.input.getX()*2, (Gdx.graphics.getHeight()- Gdx.input.getY()*2), 0);
        cam.unproject(mouse);
        if (Gdx.input.justTouched()) {
            if (mouse.x > 2 && mouse.x < 133
                    && mouse.y < 132 && mouse.y > 82) {
                gsm.set(new MenuState(gsm));
            }
        }
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
                    if(mouse.x < 157 && mouse.x > 80 && mouse.y > 577 && mouse.y < 645){
                        System.out.println("button clicked");
                        if(ButtonClicks == false) {
                            player.attack();
                            lightning = new Thunderbolt(35, 120, 200);
                            venusaur.takeDamage(lightning.getDamage());
                            ButtonClicks = true;
                        }


                    }



                    if(mouse.x < 355 && mouse.x > 279 && mouse.y > 577 && mouse.y < 645) {
                        System.out.println("button clicked");
                        if (ButtonClicks == false) {
                            player.attack();
                            electroball = new Electroball(85, 150);
                            venusaur.takeDamage(electroball.getDamage());
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
        if (mouse.x < pokemongame.WIDTH / 2){
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

        if(venusaur.getHealth() <= 0){
            gsm.set(new WinState(gsm));
        }
        venusaur.update(dt);
        if(playingState == DEFENDING){
            if(enemyattack2 != null){
                enemyattack2.update(dt);
                if(enemyattack2.collides(player.getBounds())){
                    player.takeDamage(enemyattack2.getDamage());
                }
            }else {
                for (int i = 0; i < leaves.size(); i++) {
                    leaves.get(i).update(dt);
                    if (leaves.get(i).collides(player.getBounds())) {
                        player.takeDamage(leaves.get(i).getDamage());
                    }
                }
            }
        }
        if(leaves.size() > 0) {
            if (leaves.get(0).getPosition().y < 0) {
                fight();
                leaves.clear();
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
        sb.draw(returnmenu, 0, 310, 70, 70);

        sb.draw(background, cam.position.x - (cam.viewportWidth/2), 0);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        sb.draw(venusaur.getTexture(), venusaur.getPosition().x, venusaur.getPosition().y);
        sb.draw(returnmenu, 0, 310, 70, 70);


        if(playingState == DEFENDING){
            if(enemyattack2 != null){

                sb.draw(enemyattack2.getTexture(), enemyattack2.getPosition().x, enemyattack2.getPosition().y);
            }else {
                for(int i = 0; i < leaves.size(); i++) {
                    sb.draw(leaves.get(i).getTexture(), leaves.get(i).getPosition().x, leaves.get(i).getPosition().y, 40, 40);
                }
                //System.out.println(leaves.get(i).getPosition().y);
            }
//            sb.draw(leaves.getTexture(), leaves.getPosition().x, leaves.getPosition().y);
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
        font.draw(sb, String.valueOf(venusaur.getHealth()), 2, 395);
    }
    @Override
    public void dispose() {
        background.dispose();
        player.dispose();
        venusaur.dispose();
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
