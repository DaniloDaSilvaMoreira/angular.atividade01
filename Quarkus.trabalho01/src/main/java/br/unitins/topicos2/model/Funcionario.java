package br.unitins.topicos2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Funcionario extends DefaultEntity {
    
    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false, length = 60)
    private String funcao;

    @Column(nullable = false, length = 10)
    private Integer idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    

}
