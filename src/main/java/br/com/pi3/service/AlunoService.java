package br.com.pi3.service;

import br.com.pi3.model.Aluno;
import br.com.pi3.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public Aluno salvar(Aluno aluno) {
        if (repository.existsByMatricula(aluno.getMatricula())) {
            throw new IllegalArgumentException("Matrícula já cadastrada!");
        }
        return repository.save(aluno);
    }

    public List<Aluno> listarTodos() {
        return repository.findAll();
    }
}
