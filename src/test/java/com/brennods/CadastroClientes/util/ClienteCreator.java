package com.brennods.CadastroClientes.util;

import com.brennods.CadastroClientes.Entity.Cliente;

public class ClienteCreator {
    
    public static Cliente createClienteToBeSaved() {
        return Cliente.builder()
                .nome("João Silva")
                .email("joao.silva@example.com")
                .build();
    }

    public static Cliente createClienteValid() {
        return Cliente.builder()
                .nome("João Silva")
                .email("joao.silva@example.com")
                .build();
    }

    public static Cliente createClienteToBeUpdated() {
        return Cliente.builder()
                .id(1L)
                .nome("João Silva 2")
                .email("joao.silva2@example.com")
                .build();
    }
}
