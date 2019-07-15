package com.pokemongame.game.states;

public class Message {
    private String sender;
    private String message;

    public Message(String sender, String message){
        this.message = message;
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

}
