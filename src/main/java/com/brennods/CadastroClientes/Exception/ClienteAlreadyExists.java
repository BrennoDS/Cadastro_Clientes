package com.brennods.CadastroClientes.Exception;


public class ClienteAlreadyExists extends RuntimeException{
    
    public ClienteAlreadyExists(String email){
        super("Email já cadastrado");
    }
}
