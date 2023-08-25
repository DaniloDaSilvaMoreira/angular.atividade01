package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Funcionario;

public record FuncionarioResponseDTO (
    Long id,
    String nome,
    String funcao,
    Integer idade
) {

    public static FuncionarioResponseDTO valueOf(Funcionario funcionario) {
        return new FuncionarioResponseDTO(
            funcionario.getId(), 
            funcionario.getNome(),
            funcionario.getFuncao(),
            funcionario.getIdade()
        );
    }
}
