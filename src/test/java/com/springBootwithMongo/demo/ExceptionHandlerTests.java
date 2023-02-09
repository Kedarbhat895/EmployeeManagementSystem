package com.springBootwithMongo.demo;

import com.springBootwithMongo.demo.exceptionHandler.ExceptionHandler;
import com.springBootwithMongo.demo.exceptions.EmployeeNotFoundException;
import com.springBootwithMongo.demo.exceptions.MongoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;


import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ExceptionHandlerTests {

    @InjectMocks
    private ExceptionHandler exceptionHandler;

    private org.springframework.validation.BindingResult createBindingResult(){
        org.springframework.validation.BeanPropertyBindingResult bindingResult = new org.springframework.validation.BeanPropertyBindingResult(new Object(), "objectName");
        bindingResult.addError(new FieldError("objectName", "field1", "error message 1"));
        bindingResult.addError(new FieldError("objectName", "field2", "error message 2"));
        return bindingResult;}

    @Test
    public void testForEmployeeNotFoundException() {
        EmployeeNotFoundException exception = new EmployeeNotFoundException("error");
        ResponseEntity<Object> response = exceptionHandler.handleEmployeeNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("No Employee found with these arguments", body.get("message"));
//        assertEquals(LocalDateTime.now().toString(), body.get("timestamp").toString());
    }

    @Test
    void testForUnexpectedError(){
        Exception exception = new Exception();
        ResponseEntity<Object> response = exceptionHandler.unexpectedError(exception);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("unexpectedError has occurred", body.get("message"));
    }

    @Test
    void testForMongoError(){
        MongoException exception = new MongoException();
        ResponseEntity<Object> response = exceptionHandler.handleMongoException(exception);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("MongoDB is down", body.get("message"));
    }

    @Test
    void testForInavlidArgs(){

        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null,createBindingResult());
        Map<String, String> errorMap = exceptionHandler.handleInvalidArgs(exception);
        Map<String, String> expectedErrorMap = new HashMap<>();
        expectedErrorMap.put("field1", "error message 1");
        expectedErrorMap.put("field2", "error message 2");
        assertEquals(expectedErrorMap, errorMap);

    }

}
