package com.springBootwithMongo.demo.exceptionHandler;

import com.springBootwithMongo.demo.exceptions.EmployeeNotFoundException;
import com.springBootwithMongo.demo.exceptions.MongoException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)

    public Map<String,String> handleInvalidArgs(MethodArgumentNotValidException e){
        Map<String,String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(),error.getDefaultMessage());

        });
        return errorMap;

    }


    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)

    public Map<List<String>,HttpStatus> handleInvalidParams(ConstraintViolationException e){
        Map<List<String>, HttpStatus> errorMap = new HashMap<>();
        List<String> message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        errorMap.put(message, HttpStatus.BAD_REQUEST);
        return errorMap;

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Object>  unexpectedError(Exception e){
        Map<String,Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message","unexpectedError has occurred");

        return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFound(EmployeeNotFoundException e){
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "No Employee found with these arguments");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }


    @org.springframework.web.bind.annotation.ExceptionHandler(MongoException.class)
    public ResponseEntity<Object> handleMongoException(MongoException e){
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "MongoDB is down");

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
