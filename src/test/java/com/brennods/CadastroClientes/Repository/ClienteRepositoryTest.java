package com.brennods.CadastroClientes.Repository;


import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.brennods.CadastroClientes.Entity.Cliente;
import com.brennods.CadastroClientes.Exception.ClienteNotFoundByNomeException;

import jakarta.validation.ConstraintViolationException;

@DataJpaTest
public class ClienteRepositoryTest {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Teste Save Cliente")
    void Save_Cliente_Sucesso(){
        Cliente cliente = createCliente();
        Cliente savedCliente = clienteRepository.save(cliente);
        Assertions.assertThat(savedCliente).isNotNull();
        Assertions.assertThat(savedCliente.getId()).isNotNull();
        Assertions.assertThat(savedCliente.getNome()).isEqualTo(cliente.getNome());
    }
    @Test
    @DisplayName("Teste Update Cliente")
    void Update_Cliente_Sucesso(){
        Cliente cliente = createCliente();
        Cliente savedCliente = clienteRepository.save(cliente);

        savedCliente.setNome("Maria Silva");
        savedCliente.setEmail("MariaSilva@example.com");

        Cliente updatadCliente = clienteRepository.save(savedCliente);

        Assertions.assertThat(updatadCliente).isNotNull();
        Assertions.assertThat(updatadCliente.getId()).isNotNull();
        Assertions.assertThat(updatadCliente.getNome()).isNotNull();
        Assertions.assertThat(updatadCliente.getEmail()).isNotNull();
        Assertions.assertThat(updatadCliente.getNome()).isEqualTo(savedCliente.getNome());
        Assertions.assertThat(updatadCliente.getEmail()).isEqualTo(savedCliente.getEmail());

    }

    @Test
    @DisplayName("Teste Delete Cliente")
    void Delete_Cliente_Sucesso(){
        Cliente cliente = createCliente();
        Cliente savedCliente = clienteRepository.save(cliente);

        clienteRepository.delete(savedCliente);
        
        Optional<Cliente> clienteOptional = clienteRepository.findById(savedCliente.getId());
        Assertions.assertThat(clienteOptional).isEmpty();
    }

    @Test
    @DisplayName("Teste FindByNome Cliente")
    void findByNome_Cliente_Sucesso(){
        Cliente cliente = createCliente();
        Cliente savedCliente = clienteRepository.save(cliente);
        Cliente clientePesquisado = clienteRepository.findByNome(savedCliente.getNome()).orElseThrow(() -> new ClienteNotFoundByNomeException(cliente.getNome()));
        Assertions.assertThat(clientePesquisado).isNotNull();
        Assertions.assertThat(clientePesquisado.getId()).isNotNull();
        Assertions.assertThat(clientePesquisado.getNome()).isEqualTo(cliente.getNome());
    }

    @Test
    @DisplayName("Teste FindByNome Cliente não existente - Sem exceção")
    void findByNome_Cliente_NaoEncontrado(){
        Optional<Cliente> clientePesquisado = clienteRepository.findByNome("Cliente Inexistente");
        Assertions.assertThat(clientePesquisado).isEmpty();
    }

    @Test
    @DisplayName("Teste FindByNome Cliente não existente - Lança exceção")
    void findByNome_Cliente_NaoEncontrado_Excecao(){
        Assertions.assertThatThrownBy(() -> clienteRepository.findByNome("Cliente Inexistente").orElseThrow(() -> new ClienteNotFoundByNomeException("Cliente Inexistente"))).isInstanceOf(ClienteNotFoundByNomeException.class);
    }

    @Test
    @DisplayName("Teste Save Com Nome vazio - Lança exceção")
    void Save_Cliente_Nome_Vazio(){
        Cliente cliente = new Cliente();
        Assertions.assertThatThrownBy(() -> clienteRepository.save(cliente)).isInstanceOf(ConstraintViolationException.class);
    }
    
    private Cliente createCliente() {
        return Cliente.builder()
                .nome("João Silva")
                .email("joao.silva@example.com")
                .build();
    }

    
}
