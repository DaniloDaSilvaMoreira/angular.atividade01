package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;

public record FuncionarioDTO(
    @NotBlank(message = "O campo nome deve ser informado.")
    String nome,

    @NotBlank(message = "O campo função deve ser informado.")
    String funcao,

    Integer idade
) { }
