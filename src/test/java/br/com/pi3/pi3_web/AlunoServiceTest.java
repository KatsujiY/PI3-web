package br.com.pi3.pi3_web;

import br.com.pi3.model.Aluno;
import br.com.pi3.repository.AlunoRepository;
import br.com.pi3.service.AlunoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class AlunoServiceTest {

    @Test
void testSalvarAluno() {
    // cria um mock do repository
    AlunoRepository repo = Mockito.mock(AlunoRepository.class);

    // instancia o service passando o repository falso
    AlunoService service = new AlunoService(repo);

    // cria o aluno para salvar (usando construtor SEM id)
    Aluno aluno = new Aluno("2023001", "Maria", "Sistemas de Informação");

    // define o comportamento do mock -> quando salvar, retorna com ID 1
    Mockito.when(repo.save(aluno))
           .thenReturn(new Aluno(1L, "Maria", "Sistemas de Informação"));

    // chama o método a ser testado
    Aluno salvo = service.salvar(aluno);

    // verifica se o aluno retornado não é nulo e se tem os dados esperados
    assertNotNull(salvo.getId());
    assertEquals("Maria", salvo.getNome());
    }
}
