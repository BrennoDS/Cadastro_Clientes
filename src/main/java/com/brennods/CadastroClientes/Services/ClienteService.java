package com.brennods.CadastroClientes.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.brennods.CadastroClientes.DTOs.ClientResponseDTO;
import com.brennods.CadastroClientes.DTOs.RequestPostDTO;
import com.brennods.CadastroClientes.DTOs.RequestPutDTO;
import com.brennods.CadastroClientes.Entity.Cliente;
import com.brennods.CadastroClientes.Exception.ClienteNotFoundByIdException;
import com.brennods.CadastroClientes.Exception.ClienteNotFoundByNomeException;
import com.brennods.CadastroClientes.Repository.ClienteRepository;

@Service
public class ClienteService {
    
    private final ClienteRepository clienteRepository;

    ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClientResponseDTO createClient(RequestPostDTO clienteNovo) {
        Cliente cliente = Cliente.builder()
        .nome(clienteNovo.getNome())
        .email(clienteNovo.getEmail())
        .build();
        clienteRepository.save(cliente);
        return ClientResponseDTO.toDTO(cliente);
    }

    public void deleteClient(Long id) {
        findClienteById(id);
        clienteRepository.deleteById(id);
    }

    public ClientResponseDTO findClienteById(Long id){
        return ClientResponseDTO.toDTO(clienteRepository.findById(id).orElseThrow( () -> new ClienteNotFoundByIdException(id)));
    }
    public ClientResponseDTO findClienteByNome(String nome){
        return ClientResponseDTO.toDTO(clienteRepository.findByNome(nome).orElseThrow(() -> new ClienteNotFoundByNomeException(nome)));
    }

    public ClientResponseDTO updateCliente(Long id, RequestPutDTO clienteNovo){
        ClientResponseDTO clienteRecebido = findClienteById(id);
        
        Cliente clienteAntigo = Cliente.builder()
        .id(clienteRecebido.id())
        .nome(clienteRecebido.nome())
        .email(clienteRecebido.email())
        .build();

        Cliente clienteAtualizado = Cliente.builder()
            .id(clienteAntigo.getId())
            .nome(clienteNovo.getNome() == null ? clienteAntigo.getNome() : clienteNovo.getNome())
            .email(clienteNovo.getEmail() == null ? clienteAntigo.getEmail() : clienteNovo.getEmail())
            .build();
        return ClientResponseDTO.toDTO(clienteRepository.saveAndFlush(clienteAtualizado));
    }

    public List<ClientResponseDTO> findAll() {
        return clienteRepository.findAll()
        .stream()
        .map(ClientResponseDTO::toDTO)
        .toList();
    }

}
