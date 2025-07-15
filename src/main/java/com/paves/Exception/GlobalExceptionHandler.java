package com.paves.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{

    @ExceptionHandler(UserExceptionHandler.class)
    public ResponseEntity<String> userException(UserExceptionHandler ex)
    {
        return new ResponseEntity<>(ex.getExMsg(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleExceptionHandler.class)
    public ResponseEntity<String> roleException(RoleExceptionHandler ex)
    {
        return new ResponseEntity<>(ex.getExMsg(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(PermissionExceptionHandler ex) {
        return new ResponseEntity<>(ex.getExMsg(), HttpStatus.BAD_REQUEST);
    }

}
