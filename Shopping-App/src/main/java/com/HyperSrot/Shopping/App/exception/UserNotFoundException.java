package com.HyperSrot.Shopping.App.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends Throwable{
    public UserNotFoundException(String message){
        super(message);
    }
}
