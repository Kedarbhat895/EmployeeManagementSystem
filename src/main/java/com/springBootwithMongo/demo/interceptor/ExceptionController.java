package com.springBootwithMongo.demo.interceptor;

import com.springBootwithMongo.demo.exceptions.EmployeeNotFoundException;
import com.springBootwithMongo.demo.exceptions.MongoException;
import com.springBootwithMongo.demo.model.response.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotValidException(MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValid has occurred",e.getMessage(),e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Set<String> errorMessages =
                fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toSet());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage(String.join(" , ",errorMessages));
        errorResponse.setTimeStamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);





    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> ConstraintViolationExceptionHandler(ConstraintViolationException e){
        log.error("ConstrainViolationException has occurred",e.getMessage(),e);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>  unexpectedError(Exception e){
        log.error("something unexpected {}",e.getMessage(),e);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);


    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFound(EmployeeNotFoundException e){
        log.error("MongoDB is not working {}",e.getMessage(),e);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

    }


    @ExceptionHandler(MongoException.class)
    public ResponseEntity<ErrorResponse> handleMongoException(MongoException e){
        log.error("MongoDB is not working {}",e.getMessage(),e);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }


}
