package com.brennods.CadastroClientes.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brennods.CadastroClientes.Entity.Cliente;



public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByNome(String nome);

    boolean existsByEmail(String email);
}
