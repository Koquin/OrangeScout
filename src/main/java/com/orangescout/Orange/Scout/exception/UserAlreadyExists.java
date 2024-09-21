package com.orangescout.Orange.Scout.exception;

public class UserAlreadyExists extends RuntimeException{
    public UserAlreadyExists(String message){
        super(message);
    }
}
