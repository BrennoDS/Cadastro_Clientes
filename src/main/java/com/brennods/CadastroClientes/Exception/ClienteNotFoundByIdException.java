package com.brennods.CadastroClientes.Exception;

public class ClienteNotFoundByIdException extends RuntimeException {
    public ClienteNotFoundByIdException(Long id) {
        super("Cliente não encontrado com id: " + id);
    }
    
}
