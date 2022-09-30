package br.com.senac.inicializacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.entity.Professor;
import br.com.senac.service.ProfessorService;

@Component
public class InitProfessor implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private ProfessorService professorService;
	
	//@Autowired
	//AlunoRepository repo;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		Professor prof1 = new Professor();
		prof1.setNome("Johnny Tafur");
		
		Professor prof2 = new Professor();
		prof2.setNome("Gabriella Silveira");
		
		Professor prof3 = new Professor();
		prof3.setNome("Marcelo Struct");
		
		professorService.salvar(prof1);
		professorService.salvar(prof2);
		professorService.salvar(prof3);
		
		List<Professor> listaProfessores = professorService.buscarTodosProfessores();
		
		//repo.saveAll(Arrays.asList(aluno1, aluno2, aluno3));
		
		for(Professor professor : listaProfessores) {
			System.out.println(professor.getNome());
		}
		
		//Busca por id
		Professor prof4 = professorService.buscarPorId(1);
		
		System.out.println(prof4.getNome());
		
		//deleção por id
		//professorService.deletarPorId(2);
	
		//Atualizar por id
		//aluno1.setNome("Carlos");
		//alunoService.atualizarPorId(1, aluno1);
		//
		//System.out.println("Teste Atualização: " + aluno1.getNome());
		
		Professor professorAlterado = new Professor();
		professorAlterado.setId(1);
		professorAlterado.setNome("Marlom Gomes");
		
		professorService.salvarAlteracao(professorAlterado);
		
		
	}

}
