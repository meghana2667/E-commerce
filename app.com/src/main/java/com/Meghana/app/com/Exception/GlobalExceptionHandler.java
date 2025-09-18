package com.Meghana.app.com.Exception;

import com.Meghana.app.com.Dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ErrorResponseDto>  buildResponse(Exception ex, HttpStatus status,String path){
        ErrorResponseDto error = new ErrorResponseDto(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                path
        );

        return new ResponseEntity<>(error,status);
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<ErrorResponseDto> handleResourceAlreadyEx(Exception ex, HttpServletRequest request){
        return buildResponse(ex,HttpStatus.BAD_REQUEST,request.getRequestURI());
    }

    @ExceptionHandler(ResourceNotFoundEx.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFound(Exception ex, HttpServletRequest request){
        return buildResponse(ex, HttpStatus.NOT_FOUND, request.getRequestURI());
    }


}
