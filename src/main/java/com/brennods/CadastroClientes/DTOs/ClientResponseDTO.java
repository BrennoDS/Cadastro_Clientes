package com.brennods.CadastroClientes.DTOs;

import com.brennods.CadastroClientes.Entity.Cliente;



public record ClientResponseDTO(
        Long id,
        String nome,
        String email
) {
    public static ClientResponseDTO toDTO(Cliente cliente) {
        return new ClientResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail()
        );
    }   


}
