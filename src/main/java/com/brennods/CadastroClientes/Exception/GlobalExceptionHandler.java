package com.brennods.CadastroClientes.Exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ClienteNotFoundByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFoundById(ClienteNotFoundByIdException ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(ClienteNotFoundByNomeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handlerNotFoundByName(ClienteNotFoundByNomeException ex){
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(ClienteAlreadyExists.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public Map<String, String> handlerClienteAlreadExists(ClienteAlreadyExists ex){
        return Map.of("error", ex.getMessage());
    }

}
