package com.orangescout.Orange.Scout.exception;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException (String message){
        super(message);
    }
}
