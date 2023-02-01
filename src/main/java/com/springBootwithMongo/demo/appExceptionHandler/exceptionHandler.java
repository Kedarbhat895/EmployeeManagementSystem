package com.springBootwithMongo.demo.appExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice

public class exceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public Map<String,String> handleInvalidArgs(MethodArgumentNotValidException e){
        Map<String,String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(),error.getDefaultMessage());

        });
        return errorMap;

    }


    @ExceptionHandler(ConstraintViolationException.class)

    public Map<List<String>,HttpStatus> handleInvalidParams(ConstraintViolationException e){
        Map<List<String>, HttpStatus> errorMap = new HashMap<>();
        List<String> message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        errorMap.put(message, HttpStatus.BAD_REQUEST);
        return errorMap;

    }
}
