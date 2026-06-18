package com.brennods.CadastroClientes.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.brennods.CadastroClientes.DTOs.ClientResponseDTO;
import com.brennods.CadastroClientes.DTOs.RequestPostDTO;
import com.brennods.CadastroClientes.DTOs.RequestPutDTO;
import com.brennods.CadastroClientes.Entity.Cliente;
import com.brennods.CadastroClientes.Exception.ClienteNotFoundByIdException;
import com.brennods.CadastroClientes.Exception.ClienteNotFoundByNomeException;
import com.brennods.CadastroClientes.Services.ClienteService;

@WebMvcTest(ClienteController.class)
public class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private ClienteService clienteService;



    @Test
    void deveRetornarClientePorId() throws Exception {
        ClientResponseDTO response = new ClientResponseDTO(
            1L,
            "Brenno",
            "brenno@gmail.com"
        );
        Mockito.when(clienteService.findClienteById(1L)).thenReturn(response);

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Brenno"))
                .andExpect(jsonPath("$.email").value("brenno@gmail.com"));
    }

    @Test
    void deveRetornarClientePorNome() throws Exception{
        ClientResponseDTO response = new ClientResponseDTO(
            1L,
            "Brenno",
            "brenno@gmail.com"
        );

        Mockito.when(clienteService.findClienteByNome("Brenno")).thenReturn(response);

        mockMvc.perform(get("/clientes/nome/Brenno"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Brenno"))
                .andExpect(jsonPath("$.email").value("brenno@gmail.com"));
    }

    @Test
    void deveRetornarListaCliente() throws Exception{
        ClientResponseDTO response = new ClientResponseDTO(
            1L,
            "Brenno",
            "brenno@gmail.com"
        );

        ClientResponseDTO response2 = new ClientResponseDTO(
            2L,
            "Brenno2",
            "brenno2@gmail.com"
        );



        Mockito.when(clienteService.findAll()).thenReturn(List.of(response, response2));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Brenno"))
                .andExpect(jsonPath("$[0].email").value("brenno@gmail.com"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nome").value("Brenno2"))
                .andExpect(jsonPath("$[1].email").value("brenno2@gmail.com"));
        
    }
    @Test
    void deleteDeveRetornarNoContent() throws Exception{
        mockMvc.perform(delete("/clientes/1"))
                .andExpect(status().isNoContent());
                
        Mockito.verify(clienteService)
                .deleteClient(1L);
    }

    @Test
    void retornarObjetoAtualizado() throws Exception{
        RequestPutDTO request = new RequestPutDTO(
            "brenno atualizado",
            "brennoatualizado@gmail.com"
        );

        ClientResponseDTO response = new ClientResponseDTO(
            1L,
            "brenno atualizado",
            "brennoatualizado@gmail.com"
        );

        Mockito.when(clienteService.updateCliente(Mockito.eq(1L), Mockito.any(RequestPutDTO.class))).thenReturn(response);

        mockMvc.perform(put("/clientes/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("brenno atualizado"))
                .andExpect(jsonPath("$.email").value("brennoatualizado@gmail.com"));
            

    }

    @Test
    void deveRetornarClienteQuandoCriado() throws Exception{
        RequestPostDTO request = new RequestPostDTO(
            "Brenno Novo",
            "brennonovo@gmail.com"
        );

        ClientResponseDTO response = new ClientResponseDTO(
            1L,
            "Brenno Novo",
            "brennonovo@gmail.com"
        );



        Mockito.when(clienteService.createClient(Mockito.any(RequestPostDTO.class))).thenReturn(response);

        mockMvc.perform(post("/clientes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(response.id()))
                .andExpect(jsonPath("$.nome").value(response.nome()))
                .andExpect(jsonPath("$.email").value(response.email()));

    }

    @Test
    void clienteNaoCriadoNome() throws Exception{

        RequestPostDTO request = new RequestPostDTO();
        request.setNome("Brenno");

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                        .andExpect(status().isBadRequest());   
    }


    @Test 
    void clienteNaoCriadoEmail() throws Exception{
        RequestPostDTO request = new RequestPostDTO();
        request.setNome("Brenno");

        mockMvc.perform(post("/clientes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void excecaoFindNomeClienteInexistente() throws Exception {
        
        Mockito.when(clienteService.findClienteByNome("Inexistente")).thenThrow(new ClienteNotFoundByNomeException("Inexistente"));

        mockMvc.perform(get("/clientes/nome/Inexistente"))
                .andExpect(status().isNotFound());
    }

    @Test
    void excecaoFindIdClienteInexistente() throws Exception{
        Mockito.when(clienteService.findClienteById(1L)).thenThrow(new ClienteNotFoundByIdException(1L));

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isNotFound());

    }


    
}