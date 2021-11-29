package com.example.springsecurityjwt.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity handleBadCredentials(BadCredentialsException badCredentialsException){
        return ResponseEntity.badRequest().body("Incorrect Username or Password.");
    }

    @ExceptionHandler(Exception.class)
    protected  ResponseEntity handleException(Exception e){
        return ResponseEntity.internalServerError().body("Error :"+e.getMessage());
    }


}
