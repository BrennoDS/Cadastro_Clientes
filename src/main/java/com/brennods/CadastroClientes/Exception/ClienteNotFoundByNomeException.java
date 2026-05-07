package com.brennods.CadastroClientes.Exception;

public class ClienteNotFoundByNomeException extends RuntimeException{
    public ClienteNotFoundByNomeException(String nome){
        super("Cliente não encontrado com nome: "+ nome);
    }
}
