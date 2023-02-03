package com.springBootwithMongo.demo.exceptions;

import org.springframework.http.HttpStatus;

public class MongoException extends RuntimeException{
    public MongoException(){
        super(String.format("Mongo is down because of %s", HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
