package br.unitins.topicos2.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos2.dto.FuncionarioDTO;
import br.unitins.topicos2.dto.FuncionarioResponseDTO;
import br.unitins.topicos2.model.Funcionario;
import br.unitins.topicos2.repository.FuncionarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {

    @Inject
    FuncionarioRepository FuncionarioRepository;

    @Inject
    Validator validator;

    @Override
    public List<FuncionarioResponseDTO> getAll() {
        List<Funcionario> list = FuncionarioRepository.listAll();
        return list.stream().map(e -> FuncionarioResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public FuncionarioResponseDTO findById(Long id) {
        Funcionario Funcionario = FuncionarioRepository.findById(id);
        if (Funcionario == null)
            throw new NotFoundException("Funcionario não encontrado.");
        return FuncionarioResponseDTO.valueOf(Funcionario);
    }

    @Override
    @Transactional
    public FuncionarioResponseDTO create(FuncionarioDTO FuncionarioDTO) throws ConstraintViolationException {
        validar(FuncionarioDTO);

        Funcionario entity = new Funcionario();
        entity.setNome(FuncionarioDTO.nome());
        entity.setFuncao(FuncionarioDTO.funcao());
        entity.setIdade(FuncionarioDTO.idade());
        FuncionarioRepository.persist(entity);

        return FuncionarioResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public FuncionarioResponseDTO update(Long id, FuncionarioDTO FuncionarioDTO) throws ConstraintViolationException{
        validar(FuncionarioDTO);
   
        Funcionario entity = FuncionarioRepository.findById(id);

        entity.setNome(FuncionarioDTO.nome());
        entity.setFuncao(FuncionarioDTO.funcao());
        entity.setIdade(FuncionarioDTO.idade());
        
        return FuncionarioResponseDTO.valueOf(entity);
    }

    private void validar(FuncionarioDTO FuncionarioDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<FuncionarioDTO>> violations = validator.validate(FuncionarioDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        FuncionarioRepository.deleteById(id);
    }

    @Override
    public List<FuncionarioResponseDTO> findByNome(String nome) {
        List<Funcionario> list = FuncionarioRepository.findByNome(nome);
        return list.stream().map(e -> FuncionarioResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return FuncionarioRepository.count();
    }
}