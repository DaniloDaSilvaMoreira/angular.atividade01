package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.FuncionarioDTO;
import br.unitins.topicos2.dto.FuncionarioResponseDTO;

public interface FuncionarioService {

        // recursos basicos
        List<FuncionarioResponseDTO> getAll();

        FuncionarioResponseDTO findById(Long id);
    
        FuncionarioResponseDTO create(FuncionarioDTO dto);
    
        FuncionarioResponseDTO update(Long id, FuncionarioDTO dto);
    
        void delete(Long id);
    
        // recursos extras
    
        List<FuncionarioResponseDTO> findByNome(String nome);
    
        long count();
    
}