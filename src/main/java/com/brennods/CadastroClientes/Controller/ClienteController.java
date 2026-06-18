package com.brennods.CadastroClientes.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brennods.CadastroClientes.DTOs.ClientResponseDTO;
import com.brennods.CadastroClientes.DTOs.RequestPostDTO;
import com.brennods.CadastroClientes.DTOs.RequestPutDTO;
import com.brennods.CadastroClientes.Services.ClienteService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    private final ClienteService clientesService;

    ClienteController(ClienteService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> listAll(){
        return ResponseEntity.ok(clientesService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(clientesService.findClienteById(id));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<ClientResponseDTO> findByNome(@PathVariable String nome){
        return ResponseEntity.ok(clientesService.findClienteByNome(nome));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        clientesService.deleteClient(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateCliente(@PathVariable Long id, @RequestBody  RequestPutDTO putDTO) {
        return new ResponseEntity<ClientResponseDTO>(clientesService.updateCliente(id, putDTO), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createCliente(@RequestBody @Valid RequestPostDTO postDTO) {
        return new ResponseEntity<ClientResponseDTO>(clientesService.createClient(postDTO), HttpStatus.CREATED);
    }
    



}
