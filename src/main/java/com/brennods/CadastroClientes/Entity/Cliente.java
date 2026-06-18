package com.brennods.CadastroClientes.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

//Entidade
@Entity
@Table(name = "client")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "O nome do cliente é obrigatório")
    String nome;

    @NotBlank(message = "O email do cliente é obrigatório")
    @Column(unique = true, nullable = false)
    String email;
}
