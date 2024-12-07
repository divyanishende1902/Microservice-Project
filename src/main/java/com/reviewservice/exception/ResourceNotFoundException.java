package com.reviewservice.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
    public ResourceNotFoundException(){
        super("Rating not found!!");
    }
}
