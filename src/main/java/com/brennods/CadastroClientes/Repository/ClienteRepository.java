package com.brennods.CadastroClientes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brennods.CadastroClientes.Entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
