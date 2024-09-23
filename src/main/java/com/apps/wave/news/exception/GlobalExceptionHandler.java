package com.apps.wave.news.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.apps.wave.news.dto.GeneralResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessExceptions.class)
    public ResponseEntity<GeneralResponse> handleBusinessException(BusinessExceptions ex) {
       
        return ResponseEntity.ok(new GeneralResponse(HttpStatus.BAD_REQUEST.value(),ex.message));
    
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<GeneralResponse> handleBindException(BindException ex
                                                        ) {
    
        String failedValidationFeilds = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(","));

        return ResponseEntity.ok(new GeneralResponse(HttpStatus.BAD_REQUEST.value(),  "Bind Exception" +failedValidationFeilds));
    }
}

