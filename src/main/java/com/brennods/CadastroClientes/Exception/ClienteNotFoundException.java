package com.brennods.CadastroClientes.Exception;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(Long id) {
        super("Cliente não encontrado com id: " + id);
    }
    
}
