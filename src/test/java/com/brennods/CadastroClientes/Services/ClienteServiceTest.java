package com.brennods.CadastroClientes.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.InvalidAlgorithmParameterException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.brennods.CadastroClientes.DTOs.ClientResponseDTO;
import com.brennods.CadastroClientes.DTOs.RequestPostDTO;
import com.brennods.CadastroClientes.Entity.Cliente;
import com.brennods.CadastroClientes.Exception.ClienteAlreadyExists;
import com.brennods.CadastroClientes.Exception.ClienteNotFoundByNomeException;
import com.brennods.CadastroClientes.Repository.ClienteRepository;

import tools.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void clienteCriadoComSucesso() {
        
        RequestPostDTO request = new RequestPostDTO(
            "Brenno",
            "brenno@gmail.com"
        );
        
        Cliente clienteSalvo = new Cliente(
            1L,
            "Brenno",
            "brenno@gmail.com"
        );

        Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(clienteSalvo);

        ClientResponseDTO response = clienteService.createClient(request);

        assertEquals(clienteSalvo.getId(), response.id());
        assertEquals(clienteSalvo.getNome(), response.nome());
        assertEquals(clienteSalvo.getEmail(), response.email());

        Mockito.verify(clienteRepository).save(Mockito.any(Cliente.class));
    }

    @Test 
    void excecaoCriarUsuarioComEmailJaCadastrado() throws Exception{
        RequestPostDTO request = new RequestPostDTO(
            "brenno1",
            "brenno1@gmail.com"
        );


        Mockito.when(clienteRepository.existsByEmail("brenno1@gmail.com")).thenReturn(true);

        assertThrows(ClienteAlreadyExists.class, () -> clienteService.createClient(request));

        Mockito.verify(clienteRepository, Mockito.never()).save(Mockito.any(Cliente.class));
    }

    @Test
    void testDeleteClient() {

    }

    @Test
    void testFindAll() {

    }

    @Test
    void testFindClienteById() {

    }

    @Test
    void testFindClienteByNome() {

    }

    @Test
    void testUpdateCliente() {

    }
}
