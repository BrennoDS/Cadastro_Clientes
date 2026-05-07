package com.brennods.CadastroClientes.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestPostDTO {
    @NotBlank(message = "O nome do cliente é obrigatório")
    private String nome;
    @NotBlank(message = "O email do cliente é obrigatório")
    @Email
    private String email;
}
