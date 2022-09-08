package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.entity.Aluno;
import br.com.senac.entity.Professor;
import br.com.senac.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;
	
	//Cadastrar alunos
	public Professor salvar(Professor professor) {
		return professorRepository.save(professor);
	}
	//Buscar todos alunos
	public List<Professor> buscarTodosProfessores(){
		return professorRepository.findAll();
	}
	//Buscar Aluno por id
	public Professor buscarPorId(Integer id) throws ObjectNotFoundException{
		Optional<Professor> professor = professorRepository.findById(id);
		return professor.orElseThrow(() -> new ObjectNotFoundException(1L, "Aluno não encontrado"));
	}
	//Deletar Aluno
	public void deletarPorId(Integer id) {
        Optional<Professor> professor = professorRepository.findById(id);
        professorRepository.deleteById(id);
    }
	//Atualizar Aluno
	public Professor atualizarPorId(Integer id, Aluno o) {
		Professor professor = buscarPorId(id);
		professor.setNome(o.getNome());
		return professorRepository.save(professor);
	}
	
	//Atualizar do Struct
	public Professor salvarAlteracao(Professor professorAlterado) {
		Professor professor = buscarPorId(professorAlterado.getId());
		professor.setNome(professorAlterado.getNome());
		return salvar(professor);
	}
	
}
