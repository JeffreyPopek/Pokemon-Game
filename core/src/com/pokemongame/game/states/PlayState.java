package com.pokemongame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.pokemongame.game.pokemongame;
import com.pokemongame.game.sprites.Charizard;
import com.pokemongame.game.sprites.Player;
import com.pokemongame.game.sprites.PlayerDirection;

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


    public PlayState(GameStateManager gsm) {
        super(gsm);
        playingState = PlayingState.INTRO;

        player = new Player(80, 120);
        charizard = new Charizard(80, 300);
        background = new Texture("bg.png");

        font = new BitmapFont();
        font.getData().setScale(1.5f);

        loadIntroMessages();
    }

    public void loadIntroMessages(){
        messages = new Array<Message>();
        messages.add(new Message(PLAYER, "Make your move!"));
        messages.add(new Message(PLAYER, "OK..."));
        messages.add(new Message(ENEMY, "Let's play!"));
        getNextMessage();
    }

    private void beginBattle(){
        this.playingState = PlayingState.DEFENDING;
        fight();
    }

    private void enemyFight(){
        if (currentActor == ENEMY && !(charizard.isBusy() || player.isBusy())) {
            playingState = PlayingState.DEFENDING;
            charizard.attack();

            messages.add(new Message(ENEMY, "I'm attacking!"));
            getNextMessage();

            currentActor = PLAYER;
        }
    }

    private void playerFight(){
        if(currentActor == PLAYER && !(charizard.isBusy() || player.isBusy())) {
            playingState = PlayingState.ATTACKING;
            player.attack();

            messages.add(new Message(PLAYER, "I'm attacking!"));
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
                    fight();
                case DEFENDING:
                    fight();
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
        charizard.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

        sb.begin();
        // Start Drawing
        sb.draw(background, cam.position.x - (cam.viewportWidth/2), 0);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        sb.draw(charizard.getTexture(), charizard.getPosition().x, charizard.getPosition().y);
        this.renderMessageOnSpriteBatch(sb);
        sb.end();

    }

    private void renderMessageOnSpriteBatch(SpriteBatch sb){
        if (this.currentMessage != null) {
            if (this.currentMessage.getSender() == PLAYER){
                font.draw(sb, this.currentMessage.getMessage(), 10, player.getPosition().y + 100);
            } else {
                font.draw(sb, this.currentMessage.getMessage(), 10, charizard.getPosition().y - 10);
            }

        }
    }

    @Override
    public void dispose() {
        background.dispose();
        player.dispose();
        charizard.dispose();


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
