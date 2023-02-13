package com.springBootwithMongo.demo.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ErrorResponse {

    private String message;
    private LocalDateTime timeStamp;

    private HttpStatus httpStatus;

}
